package com.kirylkhrystsenka.schoolapp.dao.impl;

import com.kirylkhrystsenka.schoolapp.dao.AbstractCrudDao;
import com.kirylkhrystsenka.schoolapp.dao.GroupDao;
import com.kirylkhrystsenka.schoolapp.dao.rowmapper.CourseRowMapper;
import com.kirylkhrystsenka.schoolapp.dao.rowmapper.GroupRowMapper;
import com.kirylkhrystsenka.schoolapp.models.entities.Course;
import com.kirylkhrystsenka.schoolapp.models.entities.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class PostgreSQLGroupDao extends AbstractCrudDao<Group, Long> implements GroupDao {
    private static final String FIND_BY_NAME = "SELECT * FROM groups WHERE group_name = ?";
    private static final String CREATE = "INSERT INTO groups (group_name) VALUES (?)";
    private static final String UPDATE = "UPDATE groups SET group_name = ? WHERE group_id = ?";
    public static final String FIND_BY_ID = "SELECT * FROM groups WHERE group_id = ?";
    private static final String FIND_ALL = "SELECT * FROM groups";
    private static final String DELETE_BY_ID = "DELETE FROM groups WHERE group_id = ?";
    private static final String DELETE_STUDENTS = "DELETE FROM students WHERE group_id = ?";
    private static final String DELETE_STUDENTS_FROM_COURSES = "DELETE FROM student_courses WHERE student_id IN" +
                                                               "(SELECT student_id FROM students WHERE group_id = ?)";
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Group> rowMapper = new GroupRowMapper();

    @Autowired
    public PostgreSQLGroupDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Group create(Group entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE, new String[]{"group_id"});
            ps.setString(1, entity.getName());
            return ps;
        }, keyHolder);

        Long generatedId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        entity.setId(generatedId);
        return entity;
    }

    @Override
    protected Group update(Group entity) {
        jdbcTemplate.update(UPDATE, entity.getName(), entity.getId());
        return entity;
    }

    @Override
    public Optional<Group> findById(Long id) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(FIND_BY_ID, rowMapper, id)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Group> findAll() {
        return jdbcTemplate.query(FIND_ALL, rowMapper);
    }

    @Override
    public Group save(Group entity) {
        return entity.getId() == null ? create(entity) : update(entity);
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(DELETE_STUDENTS_FROM_COURSES, id);

        jdbcTemplate.update(DELETE_STUDENTS, id);

        jdbcTemplate.update(DELETE_BY_ID, id);
    }

    @Override
    public Optional<Group> findByName(String name) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(FIND_BY_NAME, rowMapper, name)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
