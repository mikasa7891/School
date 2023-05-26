package com.kirylkhrystsenka.schoolapp.dao;

import com.kirylkhrystsenka.schoolapp.models.entities.Group;

import java.util.Optional;

public interface GroupDao extends CrudDao<Group, Long> {
    Optional<Group> findByName(String name);
}
