package com.hocafe.repository;

import com.hocafe.domain.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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


}
