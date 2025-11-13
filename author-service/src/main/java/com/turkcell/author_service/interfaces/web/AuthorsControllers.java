package com.turkcell.author_service.interfaces.web;

import com.turkcell.author_service.application.command.CreateAuthorCommand;
import com.turkcell.author_service.application.command.UpdateAuthorCommand;
import com.turkcell.author_service.application.dto.CreatedAuthorResponse;
import com.turkcell.author_service.application.dto.UpdatedAuthorResponse;
import com.turkcell.author_service.core.cqrs.CommandHandler;
import com.turkcell.author_service.domain.event.BookCreatedEvent;
import jakarta.validation.Valid;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/authors")
@Validated
public class AuthorsControllers {
    private final StreamBridge streamBridge;
    private final CommandHandler<CreateAuthorCommand, CreatedAuthorResponse> createdAuthorCommandHandler;
    private final CommandHandler<UpdateAuthorCommand, UpdatedAuthorResponse> updatedAuthorResponseCommandHandler;


    public AuthorsControllers(StreamBridge streamBridge, CommandHandler<CreateAuthorCommand, CreatedAuthorResponse> createdAuthorCommandHandler, CommandHandler<UpdateAuthorCommand, UpdatedAuthorResponse> updatedAuthorResponseCommandHandler) {
        this.streamBridge = streamBridge;
        this.createdAuthorCommandHandler = createdAuthorCommandHandler;
        this.updatedAuthorResponseCommandHandler = updatedAuthorResponseCommandHandler;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAuthorResponse createAuthor(@Valid @RequestBody CreateAuthorCommand command){
        return createdAuthorCommandHandler.handle(command);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdatedAuthorResponse> updateAuthor(@Valid @PathVariable UUID id, UpdateAuthorCommand command){
        UpdateAuthorCommand finalCommand = new UpdateAuthorCommand(
                id,
                command.name(),
                command.surname()
        );
        UpdatedAuthorResponse response = updatedAuthorResponseCommandHandler.handle(finalCommand);
        return ResponseEntity.ok(response);
    }

}

 /*
    * @PostMapping()
    public String createAuthor(@RequestBody CreateAuthorDto dto){
        AuthorCreatedEvent event = new AuthorCreatedEvent(dto.authorId);
        // gönderdiğimiz event msg haline çeviriyoruz
        Message<AuthorCreatedEvent> msg = MessageBuilder.withPaylqoad(event).build();
        streamBridge.send("authorCreated-out", msg);
        return dto.authorId;
    }
    @GetMapping()
    public String createAuthor(){
        return "Merhaba ozgur author";
    }
    record CreateAuthorDto(String authorId){}
    record AuthorCreatedEvent(String authorId){} */