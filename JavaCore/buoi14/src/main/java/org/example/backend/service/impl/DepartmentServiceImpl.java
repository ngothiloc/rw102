package org.example.backend.service.impl;


import org.example.backend.common.StringCommon;
import org.example.backend.repository.IDepartmentRepository;
import org.example.backend.repository.impl.DepartmentRepositoryImpl;
import org.example.backend.service.IDepartmentService;
import org.example.entity.Department;

import java.io.*;
import java.util.*;

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

            List<String> depNameInFile = new ArrayList<>();
            List<String> dataErrors = new ArrayList<>();
            List<Department> deps = repository.findAll();
            //username
            Map<String, Department> mapByUsername = new HashMap<>();
            for (Department department :deps){
                mapByUsername.put(department.getName(), department);
            }
            // neu line doc ra co du lieu thi se phan tich du lieu de lay ra cac thong tin
            while ((line = bufferedReader.readLine()) != null){
                List<String> errors = new ArrayList<>();
                String [] arrs = line.split(",");
                String departmentName = arrs[0];
                //validate > 5 < 50
                if(Objects.isNull(departmentName) || departmentName.trim().isEmpty() || departmentName.trim().length() < 5 || departmentName.trim().length() > 50){
                    errors.add("Department Name phai lon hon 5 va < 50");
                }
                //validate chi nhap so va chu
                if (!departmentName.trim().matches(StringCommon.DEPARTMENT_NAME_REGEX)){
                    errors.add("Department Name chi nhap chu va so !!!");
                }
                // unique (trung trong file va trung trong db)
                if(mapByUsername.get(departmentName) != null){
                    errors.add("Ten phong ban da ton tai!!!");
                }
                if(depNameInFile.contains(departmentName)){
                    errors.add("Ten trong file da bi trung");
                }

                //khong co loi
                if (errors.isEmpty()){
                    // chuyển dữ liệu trong file thành các dữ Liệu Liên quan đến department
                    Department department = new Department(departmentName);
                    // gọi xuống repo để zuu vao DB
                    //repository.create(department);
                    departments.add(department);
                    depNameInFile.add(departmentName);
                } else {
                    String rowError = departmentName + ","  + String.join("-", errors);
                    dataErrors.add(rowError);
                }
            }
            if (!departments.isEmpty()){
                repository. createAll(departments);
            }

            if (!dataErrors.isEmpty()){
                //xuat ra file loi
                String pathErr = "/Users/macos/Desktop/rw102/JavaCore/CSV/ErrDepartment.csv";
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathErr))){
                    bufferedWriter.write(header + "," + "error_messages");
                    bufferedWriter.newLine();
                    for (String error : dataErrors){
                        bufferedWriter.write(error);
                        bufferedWriter.newLine();
                    }
                    bufferedWriter.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return String.format("Import thanh cong %d , import that bai %d. %s", departments.size(), dataErrors.size(), !dataErrors.isEmpty() ? "Da xuat ra file loi ErrDepartment.csv" : "");
        } catch (Exception e){
            return "Loi";
        }
    }
}
