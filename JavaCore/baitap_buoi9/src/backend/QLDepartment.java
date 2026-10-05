package backend;

import entity.Department;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLDepartment implements IQLDepartment{
    Scanner sc = new Scanner(System.in);

    @Override
    public void hienThiDep() {
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

        System.out.println("+----------+---------------+");
        System.out.printf("|%10s|%15s|\n", "DepID", "DepName");
        System.out.println("+----------+---------------+");
        for(Department dep : departmentList) {
            System.out.printf("|%10d|%15s|\n", dep.getId(), dep.getDeparrmentName());
        }
        System.out.println("+----------+---------------+");
    }

    @Override
    public void timKiemDep_theoTen() {
        List<Department> departmentList = new ArrayList<>();
        Connection connection = JDBCUtils.getConnection();

        System.out.println("=== TIM KIEM DEPARTMENT - THEO TEN ===");
        System.out.print("Nhap ten dep: ");
        String depName = sc.nextLine();

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
        System.out.println("+----------+---------------+");
        System.out.printf("|%10s|%15s|\n", "DepID", "DepName");
        System.out.println("+----------+---------------+");
        for(Department dep : departmentList) {
            System.out.printf("|%10d|%15s|\n", dep.getId(), dep.getDeparrmentName());
        }
        System.out.println("+----------+---------------+");
    }

    @Override
    public void themMoiDep() {
        Connection connection = JDBCUtils.getConnection();

        System.out.println("=== THEM MOI PHONG BAN ===");

        System.out.print("Nhap id: ");
        int depId = sc.nextInt();
        sc.nextLine();

        System.out.print("Nhap ten phong ban: ");
        String depName = sc.nextLine();

        try {
            String sql = "INSERT INTO department (department_id, department_name)\n" +
                    "VALUES (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, depId);
            preparedStatement.setString(2, depName);

            int c = preparedStatement.executeUpdate();

            if (c > 0) {
                System.out.println("Them dep thanh cong!");
            } else {
                System.out.println("Them dep that bai!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void xoaDep_theoID() {
        System.out.println("==== XOA DEP THEO ID ====");
        System.out.print("Nhap ID can xoa: ");
        int depID = sc.nextInt();
        sc.nextLine();

        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM department WHERE department_id like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, depID);

            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("Xoa thanh cong!!!");
            } else {
                System.out.println("Xoa that bai");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeConnection();
        }
    }

    @Override
    public void update_Ten_PhongBan_TheoID() {
        System.out.println("==== CAP NHAT THEO ID ====");
        System.out.print("Nhap ID phong ban can cap nhat: ");
        int depID = sc.nextInt();
        sc.nextLine();

        System.out.print("Nhap ten phong ban can update: ");
        String depName = sc.nextLine();

        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "update department SET department_name = ? where department_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, depName);
            preparedStatement.setInt(2, depID);

            int c = preparedStatement.executeUpdate();
            if(c > 0 ){
                System.out.println("update thanh cong!");
            } else {
                System.out.println("update that bai!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeConnection();
        }
    }
}
