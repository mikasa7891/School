package com.kirylkhrystsenka.schoolapp.dao.impl;

import com.kirylkhrystsenka.schoolapp.dao.AbstractCrudDao;
import com.kirylkhrystsenka.schoolapp.dao.GroupDao;
import com.kirylkhrystsenka.schoolapp.models.entities.Group;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PostgreSQLGroupDao extends AbstractCrudDao<Group,Long> implements GroupDao {
    @Override
    protected Group create(Group entity) {
        return null;
    }

    @Override
    protected Group update(Group entity) {
        return null;
    }

    @Override
    public Optional<Group> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Group> findAll() {
        return null;
    }

    @Override
    public Group save(Group entity) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<Group> findByName(String name) {
        return Optional.empty();
    }
}
