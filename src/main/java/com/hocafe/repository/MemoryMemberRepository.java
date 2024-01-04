package com.hocafe.repository;

import com.hocafe.domain.Member;
import org.springframework.stereotype.Component;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private final Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }

    @Override
    public Member findByName(String name) {
        Optional<Member> result = store.values().stream().filter(member -> member.getName().equals(name)).findAny();
        return result.get();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void delete(String name) {

    }

    @Override
    public void edit(Member member) {
    }


}
