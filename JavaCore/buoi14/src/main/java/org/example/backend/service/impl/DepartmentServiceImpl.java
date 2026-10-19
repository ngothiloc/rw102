package org.example.backend.service.impl;


import org.example.backend.repository.IDepartmentRepository;
import org.example.backend.repository.impl.DepartmentRepositoryImpl;
import org.example.backend.service.IDepartmentService;
import org.example.entity.Department;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
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

    @Override
    public String importCSV(String path) {
        // Lay du lieu tu file theo đường dẫn ở path // kierm tra xem file nay có tồn tại ko,
        File file = new File(path);
        if (!path.endsWith(".csv") || !file.exists()){
            return "File khong dung dinh dang !!";
        }
        // đọc file
        String header = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            List<Department> departments = new ArrayList<>();
            header = bufferedReader.readLine(); // doc dong dau tien (tieu de)

            // neu line doc ra co du lieu thi se phan tich du lieu de lay ra cac thong tin
            while ((line = bufferedReader.readLine()) != null){
                String [] arrs = line.split(",");
                String depName = arrs[0];
                // chuyển dữ liệu trong file thành các dữ Liệu Liên quan đến department
                Department department = new Department(depName);
                // gọi xuống repo để zuu vao DB
//                repository.create(department);
                departments.add(department);
            }
            repository. createAll(departments);
            return "Import thanh cong";
        } catch (Exception e){
            return "Loi";
        }
    }
}
