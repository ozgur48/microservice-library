package com.turkcell.book_service.interfaces.web;

import com.turkcell.book_service.application.book.command.CreateBookCommand;
import com.turkcell.book_service.application.book.dto.CreatedBookResponse;
import com.turkcell.book_service.cqrs.CommandHandler;
import jakarta.validation.Valid;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@Validated
public class BooksController {
    private final CommandHandler<CreateBookCommand, CreatedBookResponse> createBookCommandHandler;
    private final StreamBridge streamBridge;


    public BooksController(CommandHandler<CreateBookCommand, CreatedBookResponse> createBookCommandHandler, StreamBridge streamBridge) {
        this.createBookCommandHandler = createBookCommandHandler;
        this.streamBridge = streamBridge;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedBookResponse createBook(@Valid @RequestBody CreateBookCommand command){
        CreatedBookResponse book = createBookCommandHandler.handle(command);
        streamBridge.send("bookCreated-out", book.title());
        return book;
    }

    @GetMapping
    public String getBook(){
        return "merhaba";
    }
}
