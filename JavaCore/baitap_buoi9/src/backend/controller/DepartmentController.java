package backend.controller;

import backend.repository.IDepartmentRepository;
import backend.service.IAccountService;
import backend.service.IDepartmentService;
import backend.service.impl.AccountServiceImpl;
import backend.service.impl.DepartmentServiceImpl;
import entity.Department;

import java.util.List;

public class DepartmentController {

    private IDepartmentService departmentService;

    public DepartmentController(){
        departmentService = new DepartmentServiceImpl();
    }

    public List<Department> hienThiDep() {
        return departmentService.hienThiDep();
    }

    public List<Department> timKiemDep_theoTen(String depName) {
        return departmentService.timKiemDep_theoTen(depName);
    }


    public boolean xoaDep_theoID(int depID) {
        return departmentService.xoaDep_theoID(depID);
    }
}
