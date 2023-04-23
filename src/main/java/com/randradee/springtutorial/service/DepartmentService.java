package com.randradee.springtutorial.service;

import com.randradee.springtutorial.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Department saveDepartment(Department department);

    List<Department> getAllDepartments();

    Department getOneDepartment(Long id);

    void deleteOneDepartment(Long id);

    Department updateOneDepartment(Long id, Department department);

    Department getDepartmentByName(String name);
}
