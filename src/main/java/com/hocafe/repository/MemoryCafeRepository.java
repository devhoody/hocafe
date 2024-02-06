package com.hocafe.repository;

import com.hocafe.domain.Cafe;
import com.hocafe.domain.Cafe;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryCafeRepository{

    private final Map<Long, Cafe> store = new HashMap<>();
    private Long sequence = 0L;

    public Cafe save(Cafe cafe) {
        cafe.setId(++sequence);
        store.put(cafe.getId(), cafe);

        return store.get(cafe.getId());
    }

    public Cafe findById(Long cafeId) {
        return store.get(cafeId);
    }

    public Cafe findByName(String name) {
        Optional<Cafe> result = store.values().stream().filter(cafe -> cafe.getCafeName().equals(name)).findAny();
        return result.get();
    }

    public List<Cafe> findAll() {
        return new ArrayList<>(store.values());
    }

    public void delete(Long cafeId) {
        store.remove(cafeId);
    }

    public void edit(Long cafeId, Cafe cafe) {
        store.put(cafeId, cafe);
    }

}
