package com.kirylkhrystsenka.schoolapp.dao.rowmapper;

import com.kirylkhrystsenka.schoolapp.models.entities.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();

        student.setId(rs.getLong("student_id"));
        student.setGroupId(rs.getLong("group_id"));
        student.setFirstName(rs.getString("first_name"));
        student.setLastName(rs.getString("last_name"));

        return student;
    }
}
