package com.turkcell.memberservice.interfaces.web;

import com.turkcell.memberservice.application.command.CreateMemberCommand;
import com.turkcell.memberservice.application.dto.CreatedMemberResponse;
import com.turkcell.memberservice.core.cqrs.CommandHandler;
import jakarta.validation.Valid;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@Validated
public class MembersController {
    private final CommandHandler<CreateMemberCommand, CreatedMemberResponse> createMemberCommandHandler;
    private final StreamBridge streamBridge;

    public MembersController(CommandHandler<CreateMemberCommand, CreatedMemberResponse> createMemberCommandHandler, StreamBridge streamBridge) {
        this.createMemberCommandHandler = createMemberCommandHandler;
        this.streamBridge = streamBridge;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedMemberResponse createMember(@Valid @RequestBody CreateMemberCommand command){
        CreatedMemberResponse member =  createMemberCommandHandler.handle(command);
        streamBridge.send("memberCreated-out", member.name());
        return member;
    }
}
