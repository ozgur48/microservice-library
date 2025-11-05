package com.turkcell.book_service.messaging.relayer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.book_service.domain.event.BookCreated;
import com.turkcell.book_service.messaging.outbox.OutboxMessage;
import com.turkcell.book_service.messaging.outbox.OutboxRepository;
import com.turkcell.book_service.messaging.outbox.OutboxStatus;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class OutboxEventRelayer {
    private final OutboxRepository outboxRepository;
    private final StreamBridge streamBridge;
    private final ObjectMapper objectMapper;


    public OutboxEventRelayer(OutboxRepository outboxRepository, StreamBridge streamBridge, ObjectMapper objectMapper) {
        this.outboxRepository = outboxRepository;
        this.streamBridge = streamBridge;
        this.objectMapper = objectMapper;
    }
    @Scheduled(fixedRate = 5000)
    public void publisPendingEvents () throws JsonProcessingException {
        System.out.println("Publish pending events worked...");
        List<OutboxMessage> pendingEvents = outboxRepository.findByStatusOrderByCreatedAtAsc(OutboxStatus.PENDING);
        for(OutboxMessage pendingEvent: pendingEvents){
            // db deki json tipi veriyi tekrar event'e dönüştür. --Deserialize
            BookCreated event = objectMapper.readValue(pendingEvent.payloadJson(), BookCreated.class);
            Message<BookCreated> message = MessageBuilder.withPayload(event).build();
            try{
                boolean isSent = streamBridge.send("bookCreated-out-0", message);
                if(!isSent){
                    pendingEvent.setRetryCount(pendingEvent.retryCount() + 1);
                    if(pendingEvent.retryCount() > 5){
                        pendingEvent.setStatus(OutboxStatus.FAILED);
                        pendingEvent.setProcessedAt(OffsetDateTime.now());
                    }
                }else {
                    pendingEvent.setStatus(OutboxStatus.SENT);
                }
                pendingEvent.setProcessedAt(OffsetDateTime.now());
                outboxRepository.save(pendingEvent);
            }catch (Exception e){
                pendingEvent.setRetryCount(pendingEvent.retryCount() + 1);
                if(pendingEvent.retryCount() > 5){
                    pendingEvent.setStatus(OutboxStatus.FAILED);
                    pendingEvent.setProcessedAt(OffsetDateTime.now());
            }
                outboxRepository.save(pendingEvent);
        }
    }
}
}
