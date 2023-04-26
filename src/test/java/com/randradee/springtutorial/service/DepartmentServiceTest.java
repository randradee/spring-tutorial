package com.randradee.springtutorial.service;

import com.randradee.springtutorial.entity.Department;
import com.randradee.springtutorial.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

// Using BDD
// Given_Preconditions_When_StateUnderTest_Then_ExpectedBehavior

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    DepartmentService departmentService;

    @MockBean
    DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .id(1L)
                .name("IT")
                .address("Ahmedabad")
                .code("IT-06")
                .build();

        Mockito.when(departmentRepository.findByNameIgnoreCase("IT")).thenReturn(department);
        Mockito.when(departmentRepository.findByNameIgnoreCase("Invalid")).thenReturn(department);
    }

    @Test
    @DisplayName("Get data based on valid department name")
    public void givenDepartmentName_whenDepartmentIsValid_thenDepartmentShouldBeFound() {
        String departmentName = "IT";
        Department found = departmentService.getDepartmentByName(departmentName);

        assertEquals(departmentName, found.getName());
    }

    @Test
    @DisplayName("Get data based on invalid department name")
    public void givenDepartmentName_whenDepartmentIsNotValid_thenDepartmentShouldNotBeFound() {
        String departmentName = "Invalid";
        Department found = departmentService.getDepartmentByName(departmentName);

        assertNotEquals(departmentName, found.getName());
    }
}