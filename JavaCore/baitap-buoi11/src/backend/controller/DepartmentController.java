package backend.controller;


import backend.service.IDepartmentService;
import backend.service.impl.DepartmentServiceImpl;
import entity.Department;

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
