package com.kirylkhrystsenka.schoolapp.dao;

import com.kirylkhrystsenka.schoolapp.dao.impl.PostgreSQLGroupDao;
import com.kirylkhrystsenka.schoolapp.models.entities.Group;

import com.kirylkhrystsenka.schoolapp.schoolconsoleapp.SchoolApplicationConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {PostgreSQLGroupDao.class, SchoolApplicationConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/clear_tables.sql", "/sql/sample_data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class PostgreSQLGroupDaoTest {

    @Autowired
    PostgreSQLGroupDao dao;

    @Test
    void findById_ExistingId_ReturnsGroup() {

        Long id = 1L;

        Optional<Group> result = dao.findById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    void findById_NonExistingId_ReturnsEmptyOptional() {

        Long id = 1000L;

        Optional<Group> result = dao.findById(id);

        assertFalse(result.isPresent());
    }

    @Test
    void findAll_ReturnsListGroups() {

        List<Group> result = dao.findAll();

        assertFalse(result.isEmpty());
    }

    @Test
    void save_NewStudent_ReturnsSavedGroupWithGeneratedId() {

        Group group = new Group();
        group.setName("Group");

        Group result = dao.save(group);

        assertNotNull(result.getId());
    }

    @Test
    void save_ExistingStudent_ReturnsUpdatedGroup() {
        Group group = new Group();
        group.setName("Group_A");

        Group result = dao.save(group);

        assertEquals(group.getId(), result.getId());
        assertEquals(group.getName(), result.getName());
    }

    @Test
    void deleteById_ExistingId_DeletesGroup() {
        Long id = 1L;

        dao.deleteById(id);

        Optional<Group> result = dao.findById(id);
        assertFalse(result.isPresent());
    }

    @Test
    void findByName_ExistingName_ReturnsGroup() {

        String name = "Group A";

        Optional<Group> result = dao.findByName(name);

        assertTrue(result.isPresent());
        assertEquals(name, result.get().getName());
    }

    @Test
    void findByName_NonExistingName_ReturnsEmptyOptional() {

        String name = "NonExisting";

        Optional<Group> result = dao.findByName(name);

        assertFalse(result.isPresent());
    }

}
