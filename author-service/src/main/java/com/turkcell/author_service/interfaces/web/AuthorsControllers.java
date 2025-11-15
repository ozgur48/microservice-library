package com.turkcell.author_service.interfaces.web;

import com.turkcell.author_service.application.command.CreateAuthorCommand;
import com.turkcell.author_service.application.command.DeleteAuthorCommand;
import com.turkcell.author_service.application.command.UpdateAuthorCommand;
import com.turkcell.author_service.application.dto.AuthorDetails;
import com.turkcell.author_service.application.dto.CreatedAuthorResponse;
import com.turkcell.author_service.application.dto.DeletedAuthorResponse;
import com.turkcell.author_service.application.dto.UpdatedAuthorResponse;
import com.turkcell.author_service.application.query.GetAuthorDetailsQuery;
import com.turkcell.author_service.core.cqrs.CommandHandler;
import com.turkcell.author_service.core.cqrs.QueryHandler;
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
    private final CommandHandler<DeleteAuthorCommand, DeletedAuthorResponse> deletedAuthorCommandHandler;

    private final QueryHandler<GetAuthorDetailsQuery, AuthorDetails> getAuthorDetailsQueryQueryHandler;



    public AuthorsControllers(StreamBridge streamBridge, CommandHandler<CreateAuthorCommand, CreatedAuthorResponse> createdAuthorCommandHandler, CommandHandler<UpdateAuthorCommand, UpdatedAuthorResponse> updatedAuthorResponseCommandHandler, CommandHandler<DeleteAuthorCommand, DeletedAuthorResponse> deletedAuthorCommandHandler, QueryHandler<GetAuthorDetailsQuery, AuthorDetails> getAuthorDetailsQueryQueryHandler) {
        this.streamBridge = streamBridge;
        this.createdAuthorCommandHandler = createdAuthorCommandHandler;
        this.updatedAuthorResponseCommandHandler = updatedAuthorResponseCommandHandler;
        this.deletedAuthorCommandHandler = deletedAuthorCommandHandler;
        this.getAuthorDetailsQueryQueryHandler = getAuthorDetailsQueryQueryHandler;
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
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDetails> getAuthor(@Valid @PathVariable UUID id, GetAuthorDetailsQuery query){
        GetAuthorDetailsQuery finalQuery = new GetAuthorDetailsQuery(id);
        AuthorDetails response = getAuthorDetailsQueryQueryHandler.handle(finalQuery);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeletedAuthorResponse> deleteAuthor(@Valid @PathVariable UUID id, DeleteAuthorCommand command){
        DeleteAuthorCommand finalCommand = new DeleteAuthorCommand(id);
        DeletedAuthorResponse response = deletedAuthorCommandHandler.handle(finalCommand);
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