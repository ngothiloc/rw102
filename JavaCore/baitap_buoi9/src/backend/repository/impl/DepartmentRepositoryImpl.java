package backend.repository.impl;

import backend.repository.IDepartmentRepository;
import entity.Department;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepositoryImpl implements IDepartmentRepository {
    @Override
    public List<Department> hienThiDep() {
        Connection connection = JDBCUtils.getConnection();
        List<Department> departmentList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM department order by department_id asc";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("department_id");
                String departmentName = resultSet.getString("department_name");

                Department dep = new Department(id, departmentName);
                departmentList.add(dep);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeConnection();
        }
        return departmentList;
    }

    @Override
    public List<Department> timKiemDep_theoTen(String depName) {
        List<Department> departmentList = new ArrayList<>();
        Connection connection = JDBCUtils.getConnection();
        try {
            String sql = "SELECT * FROM department where department_name LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + depName + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("department_id");
                String depaertmentName = resultSet.getString("department_name");

                Department dep = new Department(id, depaertmentName);
                departmentList.add(dep);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeConnection();
        }
        return departmentList;
    }

    @Override
    public boolean xoaDep_theoID(int depID) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM department WHERE department_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, depID);

            int c = preparedStatement.executeUpdate();
            if (c > 0) return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeConnection();
        }
        return false;
    }

    @Override
    public boolean update_Ten_PhongBan_TheoID(int depID, String depName) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "update department SET department_name = ? where department_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, depName);
            preparedStatement.setInt(2, depID);

            int c = preparedStatement.executeUpdate();
            if(c > 0 ) return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeConnection();
        }
        return false;
    }
}
