package backend.repository;

import entity.Department;

import java.util.List;

public interface IDepartmentRepository {
    List<Department> hienThiDep();

    List<Department> timKiemDep_theoTen(String depName);

    boolean xoaDep_theoID(int depID);

    boolean update_Ten_PhongBan_TheoID(int depID, String depName);
}
