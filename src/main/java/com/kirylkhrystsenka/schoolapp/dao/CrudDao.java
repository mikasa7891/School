package com.kirylkhrystsenka.schoolapp.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T,K>  {
    Optional<T> findById(K id);
    List<T> findAll();
    T save(T entity);
    void deleteById(K id);
}
