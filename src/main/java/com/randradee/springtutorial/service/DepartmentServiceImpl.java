package com.randradee.springtutorial.service;

import com.randradee.springtutorial.entity.Department;
import com.randradee.springtutorial.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getOneDepartment(Long id) {
        return departmentRepository.findById(id).get();
    }

    @Override
    public void deleteOneDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department updateOneDepartment(Long id, Department department) {
        Department depDB = departmentRepository.findById(id).get();


        if (Objects.nonNull(department.getName()) &&
                !"".equalsIgnoreCase(department.getName())
        ) {
            depDB.setName(department.getName());
        }
        if (Objects.nonNull(department.getAddress()) &&
                !"".equalsIgnoreCase(department.getAddress())
        ) {
            depDB.setAddress(department.getAddress());
        }
        if (Objects.nonNull(department.getCode()) &&
                !"".equalsIgnoreCase(department.getCode())
        ) {
            depDB.setCode(department.getCode());
        }

        return departmentRepository.save(depDB);
    }

    @Override
    public Department getDepartmentByName(String name) {
        return departmentRepository.findByNameIgnoreCase(name);
    }
}
