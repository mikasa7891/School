package com.kirylkhrystsenka.schoolapp.dao;

import com.kirylkhrystsenka.schoolapp.dao.impl.PostgreSQLGroupDao;
import com.kirylkhrystsenka.schoolapp.dao.impl.PostgreSQLStudentDao;
import com.kirylkhrystsenka.schoolapp.models.entities.Student;
import com.kirylkhrystsenka.schoolapp.schoolconsoleapp.SchoolApplicationConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {PostgreSQLStudentDao.class, SchoolApplicationConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/clear_tables.sql", "/sql/sample_data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class PostgreSQLStudentDaoTest {
    @Autowired
    private PostgreSQLStudentDao studentDao;

    @Test
    void create_NewStudent_ReturnsSavedStudentWithGeneratedId() {
        Student student = new Student();
        student.setGroupId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");

        Student result = studentDao.create(student);

        assertNotNull(result.getId());
        assertEquals(student.getGroupId(), result.getGroupId());
        assertEquals(student.getFirstName(), result.getFirstName());
        assertEquals(student.getLastName(), result.getLastName());
    }

    @Test
    void update_ExistingStudent_ReturnsUpdatedStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setGroupId(2L);
        student.setFirstName("Jane");
        student.setLastName("Doe");

        Student result = studentDao.update(student);

        assertEquals(student.getId(), result.getId());
        assertEquals(student.getGroupId(), result.getGroupId());
        assertEquals(student.getFirstName(), result.getFirstName());
        assertEquals(student.getLastName(), result.getLastName());
    }

    @Test
    void findById_ExistingId_ReturnsStudent() {
        Long id = 1L;

        Optional<Student> result = studentDao.findById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    void findById_NonExistingId_ReturnsEmptyOptional() {
        Long id = 1000L;

        Optional<Student> result = studentDao.findById(id);

        assertFalse(result.isPresent());
    }

    @Test
    void findAll_ReturnsListOfStudents() {
        List<Student> result = studentDao.findAll();

        assertFalse(result.isEmpty());
    }

    @Test
    void save_NewStudent_ReturnsSavedStudentWithGeneratedId() {
        Student student = new Student();
        student.setGroupId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");

        Student result = studentDao.save(student);

        assertNotNull(result.getId());
    }

    @Test
    void save_ExistingStudent_ReturnsUpdatedStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setGroupId(2L);
        student.setFirstName("Jane");
        student.setLastName("Doe");

        Student result = studentDao.save(student);

        assertEquals(student.getId(), result.getId());
        assertEquals(student.getGroupId(), result.getGroupId());
        assertEquals(student.getFirstName(), result.getFirstName());
        assertEquals(student.getLastName(), result.getLastName());
    }

    @Test
    void deleteById_ExistingId_DeletesStudent() {
        Long id = 1L;

        studentDao.deleteById(id);

        Optional<Student> result = studentDao.findById(id);
        assertFalse(result.isPresent());
    }

    @Test
    void findByName_ExistingName_ReturnsStudent() {
        String name = "John";

        Optional<Student> result = studentDao.findByName(name);

        assertTrue(result.isPresent());
        assertEquals(name, result.get().getFirstName());
    }

    @Test
    void findByName_NonExistingName_ReturnsEmptyOptional() {
        String name = "NonExisting";

        Optional<Student> result = studentDao.findByName(name);

        assertFalse(result.isPresent());
    }
}
