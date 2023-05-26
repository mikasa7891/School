package com.kirylkhrystsenka.schoolapp.dao.impl;

import com.kirylkhrystsenka.schoolapp.dao.AbstractCrudDao;
import com.kirylkhrystsenka.schoolapp.dao.CourseDao;
import com.kirylkhrystsenka.schoolapp.dao.rowmapper.CourseRowMapper;
import com.kirylkhrystsenka.schoolapp.models.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PostgreSQLCourseDao extends AbstractCrudDao<Course,Long> implements CourseDao {
    public static final String FIND_BY_NAME = "select * from courses where course_name = ?";
    public static final String CREATE = "select * from courses where course_name = ?";
    public static final String UPDATE = "select * from courses where course_name = ?";
    public static final String FIND_BY_ID = "select * from courses where course_name = ?";
    public static final String FIND_ALL = "select * from courses where course_name = ?";
    public static final String DELETE_BY_ID = "select * from courses where course_name = ?";

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Course> rowMapper = new CourseRowMapper();
    @Autowired
    public PostgreSQLCourseDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    protected Course create(Course entity) {
        return null;
    }

    @Override
    protected Course update(Course entity) {
        return null;
    }

    @Override
    public Optional<Course> findByName(String name) {
        try {
            return Optional.ofNullable(jdbcTemplate
                    .queryForObject(FIND_BY_NAME, rowMapper, name));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Course> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Course> findAll() {
        return null;
    }

    @Override
    public Course save(Course entity) {
        return entity.getId()==null?create(entity):update(entity);
    }

    @Override
    public void deleteById(Long id) {

    }
}
