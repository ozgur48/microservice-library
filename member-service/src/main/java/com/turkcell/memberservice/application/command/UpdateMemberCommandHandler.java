package com.turkcell.memberservice.application.command;

import com.turkcell.memberservice.application.dto.UpdatedMemberResponse;
import com.turkcell.memberservice.application.mapper.UpdateMemberMapper;
import com.turkcell.memberservice.core.cqrs.CommandHandler;
import com.turkcell.memberservice.domain.exception.MemberNotFoundException;
import com.turkcell.memberservice.domain.model.*;
import com.turkcell.memberservice.domain.port.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class UpdateMemberCommandHandler implements CommandHandler<UpdateMemberCommand, UpdatedMemberResponse> {

    private final MemberRepository memberRepository;
    private final UpdateMemberMapper updateMemberMapper;

    public UpdateMemberCommandHandler(MemberRepository memberRepository, UpdateMemberMapper updateMemberMapper) {
        this.memberRepository = memberRepository;
        this.updateMemberMapper = updateMemberMapper;
    }

    @Override
    public UpdatedMemberResponse handle(UpdateMemberCommand command) {
        MemberId memberId = new MemberId(command.id());

        Member member = memberRepository.findById(memberId).orElseThrow(()-> new MemberNotFoundException(memberId));
        Name name = new Name(command.name());
        Email email = new Email(command.email());
        Phone phone = new Phone(command.phone());
        Address address = new Address(command.address());

        member.updateName(name);
        member.updateEmail(email);
        member.updatePhone(phone);
        member.updateAddress(address);

        return updateMemberMapper.toResponse(member);

    }
}
