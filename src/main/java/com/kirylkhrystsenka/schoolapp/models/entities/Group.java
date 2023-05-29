package com.kirylkhrystsenka.schoolapp.models.entities;

import com.kirylkhrystsenka.schoolapp.models.HasId;



public class Group implements HasId<Long> {
    private Long id;
    private String name;

    public Group() {

    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
               "id=" + id +
               ", name='" + name + '\'' +
               '}';
    }
}
