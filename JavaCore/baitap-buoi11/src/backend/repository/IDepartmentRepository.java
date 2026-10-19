package backend.repository;


import entity.Department;

import java.util.List;

public interface IDepartmentRepository {
    List<Department> findAll();
}
