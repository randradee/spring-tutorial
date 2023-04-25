package com.randradee.springtutorial.service;

import com.randradee.springtutorial.entity.Department;
import com.randradee.springtutorial.error.DepartmentNotFoundException;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Department saveDepartment(Department department);

    List<Department> getAllDepartments();

    Department getOneDepartment(Long id) throws DepartmentNotFoundException;

    void deleteOneDepartment(Long id);

    Department updateOneDepartment(Long id, Department department) throws DepartmentNotFoundException;

    Department getDepartmentByName(String name);
}
