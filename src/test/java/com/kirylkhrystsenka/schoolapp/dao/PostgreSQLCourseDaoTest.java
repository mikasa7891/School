package com.kirylkhrystsenka.schoolapp.dao;

import com.kirylkhrystsenka.schoolapp.dao.impl.PostgreSQLCourseDao;
import com.kirylkhrystsenka.schoolapp.dao.impl.PostgreSQLGroupDao;
import com.kirylkhrystsenka.schoolapp.models.entities.Course;
import com.kirylkhrystsenka.schoolapp.schoolconsoleapp.SchoolApplicationConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {PostgreSQLCourseDao.class, SchoolApplicationConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/clear_tables.sql", "/sql/sample_data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
public class PostgreSQLCourseDaoTest {
        @Autowired
        private PostgreSQLCourseDao courseDao;

        @Test
        void create_NewCourse_ReturnsSavedCourseWithGeneratedId() {
            Course course = new Course();
            course.setName("Math");
            course.setDescription("Mathematics Course");

            Course result = courseDao.create(course);

            assertNotNull(result.getId());
            assertEquals(course.getName(), result.getName());
            assertEquals(course.getDescription(), result.getDescription());
        }

        @Test
        void update_ExistingCourse_ReturnsUpdatedCourse() {
            Course course = new Course();
            course.setId(1L);
            course.setName("Physics");
            course.setDescription("Physics Course");

            Course result = courseDao.update(course);

            assertEquals(course.getId(), result.getId());
            assertEquals(course.getName(), result.getName());
            assertEquals(course.getDescription(), result.getDescription());
        }

        @Test
        void findById_ExistingId_ReturnsCourse() {
            Long id = 1L;

            Optional<Course> result = courseDao.findById(id);

            assertTrue(result.isPresent());
            assertEquals(id, result.get().getId());
        }

        @Test
        void findById_NonExistingId_ReturnsEmptyOptional() {
            Long id = 1000L;

            Optional<Course> result = courseDao.findById(id);

            assertFalse(result.isPresent());
        }

        @Test
        void findAll_ReturnsListOfCourses() {
            List<Course> result = courseDao.findAll();

            assertFalse(result.isEmpty());
        }

        @Test
        void save_NewCourse_ReturnsSavedCourseWithGeneratedId() {
            Course course = new Course();
            course.setName("Biology");
            course.setDescription("Biology Course");

            Course result = courseDao.save(course);

            assertNotNull(result.getId());
        }

        @Test
        void save_ExistingCourse_ReturnsUpdatedCourse() {
            Course course = new Course();
            course.setId(1L);
            course.setName("Chemistry");
            course.setDescription("Chemistry Course");

            Course result = courseDao.save(course);

            assertEquals(course.getId(), result.getId());
            assertEquals(course.getName(), result.getName());
            assertEquals(course.getDescription(), result.getDescription());
        }

        @Test
        void deleteById_ExistingId_DeletesCourse() {
            Long id = 1L;

            courseDao.deleteById(id);

            Optional<Course> result = courseDao.findById(id);
            assertFalse(result.isPresent());
        }

        @Test
        void findByName_ExistingName_ReturnsCourse() {
            String name = "Mathematics";

            Optional<Course> result = courseDao.findByName(name);

            assertTrue(result.isPresent());
            assertEquals(name, result.get().getName());
        }

        @Test
        void findByName_NonExistingName_ReturnsEmptyOptional() {
            String name = "NonExisting";

            Optional<Course> result = courseDao.findByName(name);

            assertFalse(result.isPresent());
        }
}
