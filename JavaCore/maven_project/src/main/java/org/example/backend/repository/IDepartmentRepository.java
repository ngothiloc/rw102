package org.example.backend.repository;


import org.example.entity.Department;

import java.util.List;

public interface IDepartmentRepository {
    List<Department> findAll();
}
