package com.turkcell.memberservice.interfaces.web;

import com.turkcell.memberservice.application.command.CreateMemberCommand;
import com.turkcell.memberservice.application.command.DeleteMemberCommand;
import com.turkcell.memberservice.application.command.UpdateMemberCommand;
import com.turkcell.memberservice.application.dto.CreatedMemberResponse;
import com.turkcell.memberservice.application.dto.DeletedMemberResponse;
import com.turkcell.memberservice.application.dto.MemberDetails;
import com.turkcell.memberservice.application.dto.UpdatedMemberResponse;
import com.turkcell.memberservice.application.query.GetMemberDetailsQuery;
import com.turkcell.memberservice.core.cqrs.CommandHandler;
import com.turkcell.memberservice.core.cqrs.QueryHandler;
import jakarta.validation.Valid;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/members")
@Validated
public class MembersController {
    private final CommandHandler<CreateMemberCommand, CreatedMemberResponse> createMemberCommandHandler;
    private final CommandHandler<UpdateMemberCommand, UpdatedMemberResponse> updateMemberCommandHandler;
    private final CommandHandler<DeleteMemberCommand, DeletedMemberResponse> deleteMemberCommandHandler;

    private final QueryHandler<GetMemberDetailsQuery, MemberDetails> getMemberDetailsQueryHandler;
    private final StreamBridge streamBridge;

    public MembersController(CommandHandler<CreateMemberCommand, CreatedMemberResponse> createMemberCommandHandler, CommandHandler<UpdateMemberCommand, UpdatedMemberResponse> updateMemberCommandHandler, CommandHandler<DeleteMemberCommand, DeletedMemberResponse> deleteMemberCommandHandler, QueryHandler<GetMemberDetailsQuery, MemberDetails> getMemberDetailsQueryHandler, StreamBridge streamBridge) {
        this.createMemberCommandHandler = createMemberCommandHandler;
        this.updateMemberCommandHandler = updateMemberCommandHandler;
        this.deleteMemberCommandHandler = deleteMemberCommandHandler;
        this.getMemberDetailsQueryHandler = getMemberDetailsQueryHandler;
        this.streamBridge = streamBridge;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedMemberResponse createMember(@Valid @RequestBody CreateMemberCommand command){
        CreatedMemberResponse member =  createMemberCommandHandler.handle(command);
        streamBridge.send("memberCreated-out", member.name());
        return member;
    }
    @PutMapping("/{id}")
    public ResponseEntity<UpdatedMemberResponse> updateMember(@Valid @PathVariable UUID id, UpdateMemberCommand command){
        UpdateMemberCommand finalCommand = new UpdateMemberCommand(
                id,
                command.name(),
                command.email(),
                command.phone(),
                command.address()
                );
        UpdatedMemberResponse response = updateMemberCommandHandler.handle(finalCommand);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MemberDetails> getMember(@Valid @PathVariable UUID id, GetMemberDetailsQuery query){
        GetMemberDetailsQuery finalQuery = new GetMemberDetailsQuery(id);
        MemberDetails response = getMemberDetailsQueryHandler.handle(finalQuery);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DeletedMemberResponse> delete(@Valid @PathVariable UUID id, DeleteMemberCommand command){
        DeleteMemberCommand finalCommand = new DeleteMemberCommand(id);
        DeletedMemberResponse response = deleteMemberCommandHandler.handle(finalCommand);
        return ResponseEntity.ok(response);
    }
}
