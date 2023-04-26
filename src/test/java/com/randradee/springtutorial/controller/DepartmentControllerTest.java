package com.randradee.springtutorial.controller;

import com.randradee.springtutorial.entity.Department;
import com.randradee.springtutorial.error.DepartmentNotFoundException;
import com.randradee.springtutorial.service.DepartmentServiceImpl;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class DepartmentControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    DepartmentServiceImpl departmentService;

    Department department;

    @BeforeEach
    void setUp() {
        department = new Department(1L, "test", "test address", "test code");
    }

    @Test
    void givenDepartment_whenDepartmentIsValid_thenSaveDepartment() throws Exception {
        var inputDepartment = Department.builder()
                .name("test")
                .address("test address")
                .code("test code")
                .build();

        Mockito.when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);

        mockMvc.perform(post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\" : \"test\",\n" +
                                "    \"address\" : \"test address\",\n" +
                                "    \"code\" : \"test code\"\n" +
                                "}"))
                .andExpect(status().isCreated());
    }

    @Test
    void givenId_whenIdIsValid_thenGetDepartmentById() throws Exception {
        Mockito.when(departmentService.getOneDepartment(1L)).thenReturn(department);

        mockMvc.perform(get("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(department.getName()))
                .andExpect(jsonPath("$.address").value(department.getAddress()))
                .andExpect(jsonPath("$.code").value(department.getCode()));
    }
}