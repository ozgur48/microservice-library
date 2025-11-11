package com.turkcell.memberservice.domain.port;

import com.turkcell.memberservice.domain.model.Member;
import com.turkcell.memberservice.domain.model.MemberId;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(MemberId id);
    List<Member> findAll();
    List<Member> findAllPaged(Integer pageIndex, Integer pageSize);
    void delete(MemberId id);
}
