package com.kirylkhrystsenka.schoolapp.dao.impl;

import com.kirylkhrystsenka.schoolapp.dao.AbstractCrudDao;
import com.kirylkhrystsenka.schoolapp.dao.GroupDao;
import com.kirylkhrystsenka.schoolapp.dao.rowmapper.CourseRowMapper;
import com.kirylkhrystsenka.schoolapp.dao.rowmapper.GroupRowMapper;
import com.kirylkhrystsenka.schoolapp.models.entities.Course;
import com.kirylkhrystsenka.schoolapp.models.entities.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PostgreSQLGroupDao extends AbstractCrudDao<Group, Long> implements GroupDao {
    public static final String FIND_BY_NAME = "select * from groups where group_name = ?";
    public static final String CREATE = "INSERT INTO groups VALUES(?)";
    public static final String UPDATE = "UPDATE groups SET group_name = ? WHERE group_id = ?";
    public static final String FIND_BY_ID = "select * from groups where group_id = ?";
    public static final String FIND_ALL = "select * from groups";
    public static final String DELETE_BY_ID = "DELETE FROM groups WHERE group_id = ?";
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Group> rowMapper = new GroupRowMapper();

    @Autowired
    public PostgreSQLGroupDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    protected Group create(Group entity) {
        jdbcTemplate.update(CREATE, rowMapper, entity.getName());

        Long generatedId = jdbcTemplate.queryForObject("SELECT LASTVAL()", Long.class);
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
