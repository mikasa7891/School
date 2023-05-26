package com.kirylkhrystsenka.schoolapp.models.entities;

import com.kirylkhrystsenka.schoolapp.models.HasId;
import org.springframework.stereotype.Component;

public class Group implements HasId<Long> {
    private final Long id;
    private final String name;

    public Group(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Group{" +
               "id=" + id +
               ", name='" + name + '\'' +
               '}';
    }
}
