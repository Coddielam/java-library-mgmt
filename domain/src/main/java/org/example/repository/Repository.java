package org.example.repository;

import java.util.Collection;

public interface Repository<E> {
    E get(String id);
    Collection<E> get();
    E create(E entity);
    E update(E entity);
    boolean delete(E entity);
}
