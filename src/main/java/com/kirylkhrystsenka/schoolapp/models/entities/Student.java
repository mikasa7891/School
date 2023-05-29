package com.kirylkhrystsenka.schoolapp.models.entities;

import com.kirylkhrystsenka.schoolapp.models.HasId;



public class Student implements HasId<Long> {
    private Long id;
    private Long groupId;
    private String firstName;
    private String lastName;

    public Student(){

    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
