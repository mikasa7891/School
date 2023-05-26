package com.kirylkhrystsenka.schoolapp.dao.impl;

import com.kirylkhrystsenka.schoolapp.dao.AbstractCrudDao;
import com.kirylkhrystsenka.schoolapp.dao.StudentDao;
import com.kirylkhrystsenka.schoolapp.models.entities.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PostgreSQLStundetDao extends AbstractCrudDao<Student,Long> implements StudentDao {
    @Override
    protected Student create(Student entity) {
        return null;
    }

    @Override
    protected Student update(Student entity) {
        return null;
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public Student save(Student entity) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<Student> findByName(String name) {
        return Optional.empty();
    }
}
