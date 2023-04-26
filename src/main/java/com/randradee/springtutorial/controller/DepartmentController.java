package com.randradee.springtutorial.controller;

import com.randradee.springtutorial.entity.Department;
import com.randradee.springtutorial.error.DepartmentNotFoundException;
import com.randradee.springtutorial.service.DepartmentServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentServiceImpl departmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Department saveDepartment(@RequestBody @Valid Department department) {
        return departmentService.saveDepartment(department);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Department> getAllDepartments() {
        LOGGER.info("Inside getAllDepartments of DepartmentController");
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getOneDepartment(@PathVariable(value = "id") Long id) throws DepartmentNotFoundException {
        return departmentService.getOneDepartment(id);
    }

    @DeleteMapping("/{id}")
    public String deleteOneDepartment(@PathVariable(value = "id") Long id) {
        departmentService.deleteOneDepartment(id);
        return "Department deleted successfully";
    }

    @PutMapping("/{id}")
    public Department updateOneDepartment(@PathVariable(value = "id") Long id,
            @RequestBody @Valid Department department) throws DepartmentNotFoundException {
        return departmentService.updateOneDepartment(id, department);
    }

    @GetMapping("/name/{name}")
    public Department getDepartmentByName(@PathVariable(value = "name") String name) {
        return departmentService.getDepartmentByName(name);
    }
}
