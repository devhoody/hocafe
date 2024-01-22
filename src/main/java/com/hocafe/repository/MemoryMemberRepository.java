package com.hocafe.repository;

import com.hocafe.domain.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Primary
public class MemoryMemberRepository implements MemberRepository{

    private final Map<Long, Member> store = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);

        return store.get(member.getId());
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }

    @Override
    public Member findByName(String name) {
        Optional<Member> result = store.values().stream().filter(member -> member.getMemberName().equals(name)).findAny();
        return result.get();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void delete(Long memberId) {
        store.remove(memberId);
    }

    @Override
    public void edit(Long memberId, Member member) {
        store.put(memberId, member);
    }


}
