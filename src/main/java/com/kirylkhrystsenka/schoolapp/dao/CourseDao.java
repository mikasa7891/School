package com.kirylkhrystsenka.schoolapp.dao;

import com.kirylkhrystsenka.schoolapp.models.entities.Course;

import java.util.Optional;

public interface CourseDao extends CrudDao<Course, Long> {
    Optional<Course> findByName(String name);
}
