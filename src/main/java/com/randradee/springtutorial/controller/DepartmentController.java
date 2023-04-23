package com.randradee.springtutorial.controller;

import com.randradee.springtutorial.entity.Department;
import com.randradee.springtutorial.service.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentServiceImpl departmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Department saveDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getOneDepartment(@PathVariable(value = "id") Long id) {
        return departmentService.getOneDepartment(id);
    }

    @DeleteMapping("/{id}")
    public String deleteOneDepartment(@PathVariable(value = "id") Long id) {
        departmentService.deleteOneDepartment(id);
        return "Department deleted successfully";
    }

    @PutMapping("/{id}")
    public Department updateOneDepartment(@PathVariable(value = "id") Long id,
            @RequestBody Department department) {
        return departmentService.updateOneDepartment(id, department);
    }
}
