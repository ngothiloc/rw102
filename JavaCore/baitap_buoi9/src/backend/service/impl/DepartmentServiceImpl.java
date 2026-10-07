package backend.service.impl;

import backend.repository.IDepartmentRepository;
import backend.repository.impl.DepartmentRepositoryImpl;
import backend.service.IDepartmentService;
import entity.Department;

import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService {

    private IDepartmentRepository departmentRepository;

    public DepartmentServiceImpl(){
        departmentRepository = new DepartmentRepositoryImpl();
    }

    @Override
    public List<Department> hienThiDep() {
        return departmentRepository.hienThiDep();
    }

    @Override
    public List<Department> timKiemDep_theoTen(String depName) {
        return departmentRepository.timKiemDep_theoTen(depName);
    }

    @Override
    public boolean xoaDep_theoID(int depID) {
        return departmentRepository.xoaDep_theoID(depID);
    }


}
