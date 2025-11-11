package com.turkcell.memberservice.infrastructure;

import com.turkcell.memberservice.domain.model.Member;
import com.turkcell.memberservice.domain.model.MemberId;
import com.turkcell.memberservice.domain.port.MemberRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class MemberRepositoryAdapter implements MemberRepository {
    private final JpaMemberRepository jpaMemberRepository;
    private final MemberEntityMapper memberEntityMapper;

    public MemberRepositoryAdapter(JpaMemberRepository jpaMemberRepository, MemberEntityMapper memberEntityMapper) {
        this.jpaMemberRepository = jpaMemberRepository;
        this.memberEntityMapper = memberEntityMapper;
    }

    @Override
    public Member save(Member member) {
        JpaMemberEntity entity = new JpaMemberEntity();
        entity = jpaMemberRepository.save(entity);
        return memberEntityMapper.toDomain(entity);
    }

    @Override
    public Optional<Member> findById(MemberId id) {

        return jpaMemberRepository.findById(id.value()).map(memberEntityMapper::toDomain);
    }

    @Override
    public List<Member> findAll() {
        return jpaMemberRepository.findAll()
                .stream()
                .map(memberEntityMapper::toDomain).toList();
    }

    @Override
    public List<Member> findAllPaged(Integer pageIndex, Integer pageSize) {
        return jpaMemberRepository.findAll(PageRequest.of(pageIndex, pageSize))
                .stream()
                .map(memberEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void delete(MemberId id) {
        jpaMemberRepository.deleteById(id.value());
    }
}
