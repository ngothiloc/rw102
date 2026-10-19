package backend.service.impl;


import backend.repository.IDepartmentRepository;
import backend.repository.impl.DepartmentRepositoryImpl;
import backend.service.IDepartmentService;
import entity.Department;

import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService {
    private IDepartmentRepository repository;

    public DepartmentServiceImpl() {
        this.repository = new DepartmentRepositoryImpl();
    }

    @Override
    public List<Department> findAll() {
        return repository.findAll();
    }

}
