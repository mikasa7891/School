package com.kirylkhrystsenka.schoolapp.dao.impl;

import com.kirylkhrystsenka.schoolapp.dao.AbstractCrudDao;
import com.kirylkhrystsenka.schoolapp.dao.StudentDao;
import com.kirylkhrystsenka.schoolapp.dao.rowmapper.GroupRowMapper;
import com.kirylkhrystsenka.schoolapp.dao.rowmapper.StudentRowMapper;
import com.kirylkhrystsenka.schoolapp.models.entities.Group;
import com.kirylkhrystsenka.schoolapp.models.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PostgreSQLStudentDao extends AbstractCrudDao<Student, Long> implements StudentDao {
    public static final String FIND_BY_NAME = "select * from students where first_name = ?";
    public static final String CREATE = "INSERT INTO students VALUES(?,?,?)";
    public static final String UPDATE = "UPDATE students SET group_id = ?, fist_name = ?, last_name = ? WHERE student_id = ?";
    public static final String FIND_BY_ID = "select * from students where student_id = ?";
    public static final String FIND_ALL = "select * from students";
    public static final String DELETE_BY_ID = "DELETE FROM students WHERE student_id = ?";
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Student> rowMapper = new StudentRowMapper();

    @Autowired
    public PostgreSQLStudentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    protected Student create(Student entity) {
        jdbcTemplate.update(CREATE, entity.getGroupId(), entity.getFirstName(), entity.getLastName());
        Long generatedId = jdbcTemplate.queryForObject("SELECT LASTVAL()", Long.class);
        entity.setId(generatedId);

        return entity;
    }

    @Override
    protected Student update(Student entity) {
        jdbcTemplate.update(UPDATE, entity.getGroupId(), entity.getFirstName(), entity.getLastName(), entity.getId());
        return entity;
    }

    @Override
    public Optional<Student> findById(Long id) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(FIND_BY_ID, rowMapper, id)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query(FIND_ALL, rowMapper);
    }

    @Override
    public Student save(Student entity) {
        return entity.getId() == null ? create(entity) : update(entity);
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(DELETE_BY_ID, id);
    }

    @Override
    public Optional<Student> findByName(String name) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(FIND_BY_NAME, rowMapper, name)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
