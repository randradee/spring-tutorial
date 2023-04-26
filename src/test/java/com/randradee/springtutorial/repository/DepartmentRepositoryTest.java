package com.randradee.springtutorial.repository;

import com.randradee.springtutorial.entity.Department;
import jakarta.persistence.PersistenceContext;
import org.hibernate.mapping.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void givenId_whenIdIsValid_thenDepartmentShouldBeFound() {
        // Setup needed to be done here, will search for the reason later
        Department department = Department.builder()
                .name("Mechanical Engineering")
                .address("Delhi")
                .code("ME-011")
                .build();

        entityManager.persist(department);
        // End of setup

        String departmentName = "Mechanical Engineering";
        Department found = departmentRepository.findById(department.getId()).get();

        assertEquals(departmentName, found.getName());
    }
}