package com.randradee.springtutorial.service;

import com.randradee.springtutorial.entity.Department;
import com.randradee.springtutorial.error.DepartmentNotFoundException;
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
    public Department getOneDepartment(Long id) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(id);
        if (!department.isPresent()){
            throw new DepartmentNotFoundException("Department not found");
        }
        return department.get();
    }

    @Override
    public void deleteOneDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department updateOneDepartment(Long id, Department department) throws DepartmentNotFoundException {
        Optional<Department> depDB = departmentRepository.findById(id);

        if (!depDB.isPresent()){
            throw new DepartmentNotFoundException("Department not found");
        }

        if (Objects.nonNull(department.getName()) &&
                !"".equalsIgnoreCase(department.getName())
        ) {
            depDB.get().setName(department.getName());
        }
        if (Objects.nonNull(department.getAddress()) &&
                !"".equalsIgnoreCase(department.getAddress())
        ) {
            depDB.get().setAddress(department.getAddress());
        }
        if (Objects.nonNull(department.getCode()) &&
                !"".equalsIgnoreCase(department.getCode())
        ) {
            depDB.get().setCode(department.getCode());
        }
        return departmentRepository.save(depDB.get());
    }

    @Override
    public Department getDepartmentByName(String name) {
        return departmentRepository.findByNameIgnoreCase(name);
    }
}
