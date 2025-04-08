package org.example.newprojectmpp.repository;

import java.util.List;

public interface IRepository<T> {
    void add(T entity);
    boolean search(T entity);
    void remove(T entity);

    List<T> findAll();
}
