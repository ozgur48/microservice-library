package com.turkcell.memberservice.application.query;

import com.turkcell.memberservice.application.dto.MemberDetails;
import com.turkcell.memberservice.application.mapper.MemberDetailsMapper;
import com.turkcell.memberservice.core.cqrs.QueryHandler;
import com.turkcell.memberservice.domain.exception.MemberNotFoundException;
import com.turkcell.memberservice.domain.model.Member;
import com.turkcell.memberservice.domain.model.MemberId;
import com.turkcell.memberservice.domain.port.MemberRepository;

public class GetMemberDetailsQueryHandler implements QueryHandler< GetMemberDetailsQuery, MemberDetails> {
    private final MemberRepository memberRepository;
    private final MemberDetailsMapper memberDetailsMapper;
    public GetMemberDetailsQueryHandler(MemberRepository memberRepository, MemberDetailsMapper memberDetailsMapper) {
        this.memberRepository = memberRepository;
        this.memberDetailsMapper = memberDetailsMapper;
    }

    @Override
    public MemberDetails handle(GetMemberDetailsQuery query) {
        MemberId memberId = new MemberId(query.id());
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new MemberNotFoundException(memberId));
        return memberDetailsMapper.toResponse(member);
    }
}
