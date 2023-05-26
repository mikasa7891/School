package com.kirylkhrystsenka.schoolapp.dao;

import com.kirylkhrystsenka.schoolapp.models.entities.Student;

import java.util.Optional;

public interface StudentDao extends CrudDao<Student, Long> {
    Optional<Student> findByName(String name);
}
