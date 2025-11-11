package com.turkcell.memberservice.application.command;

import com.turkcell.memberservice.application.dto.CreatedMemberResponse;
import com.turkcell.memberservice.application.mapper.CreateMemberMapper;
import com.turkcell.memberservice.core.cqrs.CommandHandler;
import com.turkcell.memberservice.domain.model.Member;
import com.turkcell.memberservice.domain.port.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateMemberCommandHandler implements CommandHandler<CreateMemberCommand, CreatedMemberResponse> {
    private final MemberRepository memberRepository;
    private final CreateMemberMapper createMemberMapper;

    public CreateMemberCommandHandler(MemberRepository memberRepository, CreateMemberMapper createMemberMapper) {
        this.memberRepository = memberRepository;
        this.createMemberMapper = createMemberMapper;
    }

    @Override
    public CreatedMemberResponse handle(CreateMemberCommand command) {
        Member member = createMemberMapper.toDomain(command);
        member = memberRepository.save(member);
        return createMemberMapper.toResponse(member);
    }
}
