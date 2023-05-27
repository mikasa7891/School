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
public class PostgreSQLCourseDao extends AbstractCrudDao<Course, Long> implements CourseDao {
    public static final String FIND_BY_NAME = "select * from courses where course_name = ?";
    public static final String CREATE = "INSERT INTO courses VALUES(?,?)";
    public static final String UPDATE = "UPDATE courses SET course_name = ?, course_description = ? WHERE course_id = ?";
    public static final String FIND_BY_ID = "select * from courses where course_id = ?";
    public static final String FIND_ALL = "select * from courses";
    public static final String DELETE_BY_ID = "DELETE FROM courses WHERE course_id = ?";

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Course> rowMapper = new CourseRowMapper();

    @Autowired
    public PostgreSQLCourseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    protected Course create(Course entity) {
        jdbcTemplate.update(CREATE, entity.getName(), entity.getDescription());

        Long generatedId = jdbcTemplate.queryForObject("SELECT LASTVAL()", Long.class);
        entity.setId(generatedId);

        return entity;
    }

    @Override
    protected Course update(Course entity) {
        jdbcTemplate.update(UPDATE, entity.getName(), entity.getDescription(), entity.getId());
        return entity;
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
        try {
            return Optional.ofNullable(jdbcTemplate
                    .queryForObject(FIND_BY_ID, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Course> findAll() {
        return jdbcTemplate.query(FIND_ALL, new CourseRowMapper());
    }

    @Override
    public Course save(Course entity) {
        return entity.getId() == null ? create(entity) : update(entity);
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(DELETE_BY_ID, id);
    }
}
