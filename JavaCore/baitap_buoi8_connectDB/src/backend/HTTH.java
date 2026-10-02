package backend;

import entity.Account;
import entity.Department;
import entity.Position;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HTTH implements IHTTH {
    Scanner sc = new Scanner(System.in);

    @Override
    public void hienThiToanBoAccount() {
        List<Account> accountList = new ArrayList<>();

        //tao ket noi
        try {
            String url = "jdbc:mysql://localhost:3306/rw102";
            String username = "root";
            String password = "311004";
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "select acc.account_id, acc.email, acc.username, acc.full_name, dep.department_name, pos.position_name, acc.created_date from account acc\n" +
                    "left join department dep ON acc.department_id = dep.department_id\n" +
                    "left join position pos ON acc.position_id = pos.position_id;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("account_id");
                String email = resultSet.getString("email");
                String username_acc = resultSet.getString("username");
                String fullName = resultSet.getString("full_name");
                String departmentName = resultSet.getString("department_name");
                String positionName = resultSet.getString("position_name");
                LocalDate created_date = resultSet.getDate("created_date").toLocalDate();

                Department department = new Department(departmentName);
                Position position = new Position(positionName);

                Account acc =new Account(id, email, username_acc, fullName, department, position, created_date);
                accountList.add(acc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("=== HIEN THI TOAN BO ACCOUNY ===");
        System.out.println("+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+");
        System.out.printf("|%35s|%35s|%35s|%35s|%35s|%35s|\n", "AccountID", "Email", "Username", "Full Name", "Department Name", "Position Name");
        System.out.println("+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+");
        for (Account acc : accountList) {
            System.out.printf("|%35s|%35s|%35s|%35s|%35s|%35s|\n", acc.getId(), acc.getEmail(), acc.getUsername(), acc.getFullName(), acc.getDepartment(), acc.getPosition());
        }
        System.out.println("+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+");
    }

    @Override
    public void timKiemAccount_TheoUser() {
        List<Account> accountList = new ArrayList<>();
        System.out.println("=== TIM KIEM ACCOUNT - THEO TEN ===");
        System.out.print("Nhap ten can tim kiem: ");
        String ten = sc.nextLine();

        //tao ket noi
        try {
            String url = "jdbc:mysql://localhost:3306/rw102";
            String username = "root";
            String password = "311004";

            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "select acc.account_id, acc.email, acc.username, acc.full_name, dep.department_name, pos.position_name, acc.created_date from account acc\n" +
                    "join department dep ON acc.department_id = dep.department_id\n" +
                    "join position pos ON acc.position_id = pos.position_id\n" +
                    "where acc.username like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + ten + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("account_id");
                String email = resultSet.getString("email");
                String username_acc = resultSet.getString("username");
                String fullName = resultSet.getString("full_name");
                String departmentName = resultSet.getString("department_name");
                String positionName = resultSet.getString("position_name");
                LocalDate created_date = resultSet.getDate("created_date").toLocalDate();

                Department department = new Department(departmentName);
                Position position = new Position(positionName);

                Account acc =new Account(id, email, username_acc, fullName, department, position, created_date);
                accountList.add(acc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("=== HIEN THI DEPARTMENT ===");
        System.out.println("+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+");
        System.out.printf("|%35s|%35s|%35s|%35s|%35s|%35s|\n", "AccountID", "Email", "Username", "Full Name", "Department Name", "Position Name");
        System.out.println("+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+");
        for (Account acc : accountList) {
            System.out.printf("|%35s|%35s|%35s|%35s|%35s|%35s|\n", acc.getId(), acc.getEmail(), acc.getUsername(), acc.getFullName(), acc.getDepartment(), acc.getPosition());
        }
        System.out.println("+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+");
    }

    @Override
    public void hienThiDepartment() {
        List<Department> departmentList = new ArrayList<>();


        // tao ket noi den database
        try {
            //b1 lay du lieu
            String url = "jdbc:mysql://localhost:3306/rw102";
            String username = "root";
            String password = "311004";

            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "select * from department";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("department_id");
                String departmentName = resultSet.getString("department_name");

                Department department = new Department(id, departmentName);
                departmentList.add(department);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("+-----------------------------------+-----------------------------------+");
        System.out.printf("|%35s|%35s|\n", "DepartmentID", "DepartmentName");
        System.out.println("+-----------------------------------+-----------------------------------+");
        for (Department dep : departmentList) {
            System.out.printf("|%35s|%35s|\n", dep.getId(), dep.getDeparrmentName());
        }
        System.out.println("+-----------------------------------+-----------------------------------+");
    }

    @Override
    public void timKiemDepartment_TheoTen() {
        List<Department> departmentList = new ArrayList<>();
        System.out.println("=== TIM KIEM DEPARTMENT  - THEO TEN ===");
        System.out.print("Nhap ten department: ");
        String depten = sc.nextLine();

        // tao ket noi den database
        try {
            //b1 lay du lieu
            String url = "jdbc:mysql://localhost:3306/rw102";
            String username = "root";
            String password = "311004";

            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "select * from department where department_name like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + depten + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("department_id");
                String departmentName = resultSet.getString("department_name");

                Department department = new Department(id, departmentName);
                departmentList.add(department);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("+-----------------------------------+-----------------------------------+");
        System.out.printf("|%35s|%35s|\n", "DepartmentID", "DepartmentName");
        System.out.println("+-----------------------------------+-----------------------------------+");
        for (Department dep : departmentList) {
            System.out.printf("|%35s|%35s|\n", dep.getId(), dep.getDeparrmentName());
        }
        System.out.println("+-----------------------------------+-----------------------------------+");
    }
}
