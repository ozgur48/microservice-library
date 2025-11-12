package com.turkcell.book_service.interfaces.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.book_service.application.book.command.CreateBookCommand;
import com.turkcell.book_service.application.book.command.UpdateBookCommand;
import com.turkcell.book_service.application.book.dto.CreatedBookResponse;
import com.turkcell.book_service.application.book.dto.UpdatedBookResponse;
import com.turkcell.book_service.cqrs.CommandHandler;
import com.turkcell.book_service.messaging.outbox.OutboxMessage;
import com.turkcell.book_service.messaging.outbox.OutboxRepository;
import jakarta.validation.Valid;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/books")
@Validated
public class BooksController {
    private final CommandHandler<CreateBookCommand, CreatedBookResponse> createBookCommandHandler;
    private final CommandHandler<UpdateBookCommand, UpdatedBookResponse> updateBookCommandHandler;
    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;
    private final StreamBridge streamBridge;


    public BooksController(CommandHandler<CreateBookCommand, CreatedBookResponse> createBookCommandHandler, CommandHandler<UpdateBookCommand, UpdatedBookResponse> updateBookCommandHandler, OutboxRepository outboxRepository, ObjectMapper objectMapper, StreamBridge streamBridge) {
        this.createBookCommandHandler = createBookCommandHandler;
        this.updateBookCommandHandler = updateBookCommandHandler;
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
        this.streamBridge = streamBridge;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedBookResponse createBook(@Valid @RequestBody CreateBookCommand command) {
        CreatedBookResponse book = createBookCommandHandler.handle(command);
        streamBridge.send("bookCreated-out", book.title());
        return book;
    }
    @PutMapping("/{id}")
    public ResponseEntity<UpdatedBookResponse> updateBook(@Valid @PathVariable UUID id, @RequestBody UpdateBookCommand command){
        UpdateBookCommand finalCommand = new UpdateBookCommand(
                id,
                command.title(),
                command.language(),
                command.year()
        );
        UpdatedBookResponse response = updateBookCommandHandler.handle(finalCommand);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public String getBook(){
        return "Merhaba benim yeni kitabım: ";
    }
    /*
    @PostMapping
    public String createNewBook(@Valid @RequestBody BookDto dto) throws JsonProcessingException {
        BookCreateEvent event = new BookCreateEvent(dto.bookId);
        OutboxMessage message = new OutboxMessage();
        message.setAggregateId(UUID.randomUUID());
        message.setAggregateType("Book");
        message.setEventId(UUID.randomUUID());
        message.setAggregateType("BookCreated");
        message.setPayloadJson(objectMapper.writeValueAsString(event));
        outboxRepository.save(message);
        return dto.bookId;
    }

    record BookDto(String bookId) {
    }
    record BookCreateEvent(String bookId){}
    */
}