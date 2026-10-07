package backend.service;

import entity.Department;

import java.util.List;

public interface IDepartmentService {
    List<Department> hienThiDep();

    List<Department> timKiemDep_theoTen(String depName);

    boolean xoaDep_theoID(int depID);
}
