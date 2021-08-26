package com.n26.repository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

public interface AbstractRepository<T> {

    UUID save(T model);

    void deleteAll();

    boolean isEmpty();

    ConcurrentMap<String, T> findAll();

    default List<T> getList() {
        return Collections.emptyList();
    }
}
