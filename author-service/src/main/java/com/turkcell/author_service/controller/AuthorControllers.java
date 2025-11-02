package com.turkcell.author_service.controller;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorControllers {
    private final StreamBridge streamBridge;

    public AuthorControllers(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @PostMapping()
    public String createAuthor(@RequestBody CreateAuthorDto dto){
        AuthorCreatedEvent event = new AuthorCreatedEvent(dto.authorId);
        streamBridge.send("authorCreated-out", event);
        return dto.authorId;
    }
    @GetMapping()
    public String createAuthor(){
        return "Merhaba ozgur author";
    }
    record CreateAuthorDto(String authorId){}
    record AuthorCreatedEvent(String authorId){}
}
