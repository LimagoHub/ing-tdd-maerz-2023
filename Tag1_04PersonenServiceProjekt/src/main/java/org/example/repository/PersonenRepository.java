package org.example.repository;

import java.util.Optional;

public interface PersonenRepository {

    void save(Person person);
    void update(Person person);

    boolean remove(String id);

    Optional<Person> findById(String id);

    Iterable<Person> findAll();
}
