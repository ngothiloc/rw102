package backend.service;


import entity.Department;

import java.util.List;

public interface IDepartmentService {
    List<Department> findAll();
}
