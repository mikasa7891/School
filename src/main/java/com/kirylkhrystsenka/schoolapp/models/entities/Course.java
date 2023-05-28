package com.kirylkhrystsenka.schoolapp.models.entities;

import com.kirylkhrystsenka.schoolapp.models.HasId;
import lombok.Builder;
import lombok.Data;

public class Course implements HasId<Long> {
    private Long id;
    private String name;
    private String description;

    public Course() {

    }

    public Course(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
