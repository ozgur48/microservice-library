package com.turkcell.fineservice.infrastructure.messaging.relayer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turkcell.fineservice.infrastructure.messaging.outbox.OutboxMessage;
import com.turkcell.fineservice.infrastructure.messaging.outbox.OutboxRepository;
import com.turkcell.fineservice.infrastructure.messaging.outbox.OutboxStatus;
import org.springframework.messaging.Message;
import java.time.OffsetDateTime;
import org.springframework.messaging.support.MessageBuilder;

@Service
public class OutboxEventRelayer {
    private static final Logger log = LoggerFactory.getLogger(OutboxEventRelayer.class);
    private final OutboxRepository outboxRepository;
    private final StreamBridge streamBridge;

    private static final int MAX_RETRY_COUNT = 5;

    public OutboxEventRelayer(OutboxRepository outboxRepository, StreamBridge streamBridge) {
        this.outboxRepository = outboxRepository;
        this.streamBridge = streamBridge;
    }

    @Scheduled(fixedDelayString = "${outbox.scheduler.fixed-delay:5000}")
    @Transactional
    public void publishPendingEvents(){
        List<OutboxMessage> pendingEvents = outboxRepository.findByStatusOrderByCreatedAtAsc(OutboxStatus.PENDING);
        if(pendingEvents.isEmpty()){
            return;
        }
        log.info("Processing {} pending messsages from Outbox.", pendingEvents.size());
        for(OutboxMessage pending: pendingEvents){
            boolean successfullySent = false;
            try{
                 // 2. KAFKA MESAJINI OLUŞTUR
                // Kafka Key ve Idempotency için eventId'yi header olarak eklemek önemlidir.
                // streamBridge, Spring Cloud Stream'in çıktı binding'ine gönderir.
                Message<?> message = MessageBuilder.withPayload(pending.payloadJson())
                    .setHeader("event-id", pending.eventId().toString())
                    .setHeader("aggregate-id", pending.aggregateId().toString())
                    .build();
                successfullySent = streamBridge.send(pending.aggregateType().toLowerCase() + "Events-out-0", message);
            }catch(Exception e){
                log.error("Failed to process or send Outbox message ID: {}", pending.eventId(), e);
                successfullySent = false;
            }
            if(successfullySent){
                pending.setStatus(OutboxStatus.SENT);
                pending.setProcessedAt(OffsetDateTime.now());
                log.debug("Successfully published Outbox message ID: {}", pending.id());
            }
            else{
                pending.setRetryCount(pending.retryCount() + 1);
                if(pending.retryCount() > MAX_RETRY_COUNT){
                    pending.setStatus(OutboxStatus.FAILED);
                    log.warn("Outbox message ID: {} failed after {} retries. Marked as FAILED.", pending.id(), MAX_RETRY_COUNT);
                }
            }
             // transactional sayesinde döngü sonunda hepsi tek bir transactionda commit edilir
             outboxRepository.save(pending);
        }

    }
    
}
