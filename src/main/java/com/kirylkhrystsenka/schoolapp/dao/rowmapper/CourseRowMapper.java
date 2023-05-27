package com.kirylkhrystsenka.schoolapp.dao.rowmapper;

import com.kirylkhrystsenka.schoolapp.models.entities.Course;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CourseRowMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = new Course();

        course.setId(rs.getLong("id"));
        course.setName(rs.getString("name"));
        course.setDescription(rs.getString("description"));
        return course;
    }
}
