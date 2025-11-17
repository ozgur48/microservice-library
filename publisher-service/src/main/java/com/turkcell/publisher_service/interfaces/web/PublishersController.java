package com.turkcell.publisher_service.interfaces.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.publisher_service.application.command.CreatePublisherCommand;
import com.turkcell.publisher_service.application.command.DeletePublisherCommand;
import com.turkcell.publisher_service.application.command.UpdatePublisherCommand;
import com.turkcell.publisher_service.application.command.UpdatePublisherCommandHandler;
import com.turkcell.publisher_service.application.dto.CreatedPublisherResponse;
import com.turkcell.publisher_service.application.dto.DeletedPublisherResponse;
import com.turkcell.publisher_service.application.dto.UpdatedPublisherResponse;
import com.turkcell.publisher_service.cqrs.CommandHandler;
import com.turkcell.publisher_service.messaging.OutboxRepository;
import jakarta.validation.Valid;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/publishers")
@Validated
public class PublishersController {
    private final CommandHandler<CreatePublisherCommand, CreatedPublisherResponse> createPublisherCommandHandler;
    private final CommandHandler<UpdatePublisherCommand, UpdatedPublisherResponse> updatePublisherCommandHandler;
    private final CommandHandler<DeletePublisherCommand, DeletedPublisherResponse> deletePublisherCommandHandler;

    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;
    private final StreamBridge streamBridge;


    public PublishersController(CommandHandler<CreatePublisherCommand,
                                        CreatedPublisherResponse> createPublisherCommandHandler,
                                CommandHandler<UpdatePublisherCommand,
                                        UpdatedPublisherResponse> updatePublisherCommandHandler,
                                CommandHandler<DeletePublisherCommand,
                                        DeletedPublisherResponse> deletePublisherCommandHandler,
                                OutboxRepository outboxRepository,
                                ObjectMapper objectMapper,
                                StreamBridge streamBridge) {
        this.createPublisherCommandHandler = createPublisherCommandHandler;
        this.updatePublisherCommandHandler = updatePublisherCommandHandler;
        this.deletePublisherCommandHandler = deletePublisherCommandHandler;
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
        this.streamBridge = streamBridge;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedPublisherResponse createPublisher(@Valid @RequestBody CreatePublisherCommand command) {
        CreatedPublisherResponse publisher = createPublisherCommandHandler.handle(command);
        streamBridge.send("publisherCreated-out", publisher.name());
        return publisher;
    }

    @GetMapping()
    public String getPublisher() {
        return "publisher'ın getPublisher() metodu";
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdatedPublisherResponse> updatePublisher(@Valid @PathVariable UUID id,
                                                                    UpdatePublisherCommand updateCommand){
        UpdatePublisherCommand finalCommand = new UpdatePublisherCommand(
                id,
                updateCommand.name(),
                updateCommand.address()
        );
        UpdatedPublisherResponse response = updatePublisherCommandHandler.handle(finalCommand);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeletedPublisherResponse> delete(@Valid @PathVariable UUID id, DeletePublisherCommand command){
        DeletePublisherCommand finalCommand = new DeletePublisherCommand(id);
        DeletedPublisherResponse response = deletePublisherCommandHandler.handle(finalCommand);
        return ResponseEntity.ok(response);
    }
}