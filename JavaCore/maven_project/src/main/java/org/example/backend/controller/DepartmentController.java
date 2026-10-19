package org.example.backend.controller;


import org.example.backend.service.IDepartmentService;
import org.example.backend.service.impl.DepartmentServiceImpl;
import org.example.entity.Department;

import java.util.List;

public class DepartmentController {
    private IDepartmentService service;

    public DepartmentController() {
        this.service = new DepartmentServiceImpl();
    }

    public List<Department> findAll() {
        return service.findAll();
    }
}
