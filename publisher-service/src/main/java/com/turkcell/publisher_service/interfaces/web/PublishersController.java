package com.turkcell.publisher_service.interfaces.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.publisher_service.application.command.CreatePublisherCommand;
import com.turkcell.publisher_service.application.dto.CreatedPublisherResponse;
import com.turkcell.publisher_service.cqrs.CommandHandler;
import com.turkcell.publisher_service.messaging.OutboxRepository;
import jakarta.validation.Valid;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/publishers")
@Validated
public class PublishersController {
    private final CommandHandler<CreatePublisherCommand, CreatedPublisherResponse> createPublisherCommandHandler;
    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;
    private final StreamBridge streamBridge;


    public PublishersController(CommandHandler<CreatePublisherCommand,
                                        CreatedPublisherResponse> createBookCommandHandler,
                                OutboxRepository outboxRepository,
                                ObjectMapper objectMapper,
                                StreamBridge streamBridge) {
        this.createPublisherCommandHandler = createBookCommandHandler;
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
        this.streamBridge = streamBridge;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedPublisherResponse createBook(@Valid @RequestBody CreatePublisherCommand command) {
        CreatedPublisherResponse publisher = createPublisherCommandHandler.handle(command);
        streamBridge.send("publisherCreated-out", publisher.id() + publisher.name());
        return publisher;
    }

    @GetMapping()
    public String getPublisher() {
        return "publisher'ın getPublisher() metodu";
    }
}