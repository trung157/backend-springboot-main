package com.spring.demo.service;

import java.util.Optional;

public interface GeneralService<T> {
	Iterable<T> findAll();

    Optional<T> findById(int id);

    T save(T t);

    void remove(int id);
}
