package com.turkcell.memberservice.application.command;

import com.turkcell.memberservice.application.dto.DeletedMemberResponse;
import com.turkcell.memberservice.core.cqrs.CommandHandler;
import com.turkcell.memberservice.domain.exception.MemberNotFoundException;
import com.turkcell.memberservice.domain.model.MemberId;
import com.turkcell.memberservice.domain.port.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteMemberCommandHandler implements CommandHandler<DeleteMemberCommand, DeletedMemberResponse> {
    private final MemberRepository memberRepository;


    public DeleteMemberCommandHandler(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public DeletedMemberResponse handle(DeleteMemberCommand command) {
        MemberId memberId = new MemberId(command.id());
        memberRepository.findById(memberId).orElseThrow(()-> new MemberNotFoundException(memberId));
        memberRepository.delete(memberId);
        return new DeletedMemberResponse(memberId.value());
    }
}
