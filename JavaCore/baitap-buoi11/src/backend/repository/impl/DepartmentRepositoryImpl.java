package backend.repository.impl;


import backend.repository.IDepartmentRepository;
import entity.Department;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepositoryImpl implements IDepartmentRepository {

    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from department";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int departmentId = resultSet.getInt("department_id");
                String departmentName = resultSet.getString("department_name");
                Department department = new Department(departmentId, departmentName);
                departments.add(department);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return departments;
    }
}
