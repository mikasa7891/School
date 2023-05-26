package com.kirylkhrystsenka.schoolapp.models.entities;

import com.kirylkhrystsenka.schoolapp.models.HasId;
import org.springframework.stereotype.Component;

public class Student implements HasId<Long> {
    private final Long id;
    private final Long groupId;
    private final String firstName;
    private final String lastName;

    public Student(Long id, Long groupId, String firstName, String lastName) {
        this.id = id;
        this.groupId = groupId;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public Long getGroupId() {
        return groupId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
               "id=" + id +
               ", groupId=" + groupId +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               '}';
    }
}
