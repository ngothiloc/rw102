package backend;

import entity.Account;
import entity.Department;
import entity.Position;
import utils.JDBCUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLAccount implements IQLAccount{
    Scanner sc = new Scanner(System.in);
    IQLDepartment iqlDepartment = new QLDepartment();

    public void hienThiPosition(){
        List<Position> positionList = new ArrayList<>();
        Connection connection = JDBCUtils.getConnection();

        try {
            String sql = "SELECT * FROM position order by position_id asc";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("position_id");
                String positionName = resultSet.getString("position_name");

                Position pos = new Position(id, positionName);
                positionList.add(pos);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeConnection();
        }

        System.out.println("+----------+---------------+");
        System.out.printf("|%10s|%15s|\n", "PosID", "PosName");
        System.out.println("+----------+---------------+");
        for (Position pos : positionList) {
            System.out.printf("|%10d|%15s|\n", pos.getId(), pos.getPositionName());
        }
        System.out.println("+----------+---------------+");
    }
//    ==============================================
    @Override
    public void hienThiToanBoAccount() {
        List<Account> accountList = new ArrayList<>();
        Connection connection = JDBCUtils.getConnection();

        try {
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
        } finally {
            JDBCUtils.closeConnection();
        }
        System.out.println("=== HIEN THI TOAN BO ACCOUNT ===");
        System.out.println("+--------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+");
        System.out.printf("|%20s|%35s|%35s|%35s|%35s|%35s|\n", "AccountID", "Email", "Username", "Full Name", "Department Name", "Position Name");
        System.out.println("+--------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+");
        for (Account acc : accountList) {
            System.out.printf("|%20s|%35s|%35s|%35s|%35s|%35s|\n", acc.getId(), acc.getEmail(), acc.getUsername(), acc.getFullName(), acc.getDepartment().getDeparrmentName(), acc.getPosition().getPositionName());
        }
        System.out.println("+--------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+");

    }

    @Override
    public void timKiemAccount_TheoUser() {
        List<Account> accountList = new ArrayList<>();
        Connection connection = JDBCUtils.getConnection();

        System.out.println("=== TIM KIEM ACCOUNT - THEO TEN ===");
        System.out.print("Nhap ten can tim kiem: ");
        String ten = sc.nextLine();


        try {
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
        } finally {
            JDBCUtils.closeConnection();
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
    public void themMoiAcc() {
        Connection connection = JDBCUtils.getConnection();

        System.out.println("==== THEM MOI CAN BO ====");

        System.out.print("Nhap email: ");
        String email = sc.nextLine();

        System.out.print("Nhap username: ");
        String username = sc.nextLine();

        System.out.print("Nhap ho va ten: ");
        String fullname = sc.nextLine();

        System.out.println("---Chon Department---");
        iqlDepartment.hienThiDep();
        System.out.print("Nhap department: ");
        int departmentId = sc.nextInt();
        sc.nextLine();

        System.out.println("---Chon Position---");
        hienThiPosition();
        System.out.print("Nhap Position: ");
        int positionID = sc.nextInt();
        sc.nextLine();

        try {
            String sql = "INSERT INTO account\n" +
                    "(email, username, full_name, department_id, position_id, created_date)\n" +
                    "VALUES\n" +
                    "(?, ?, ?, ?, ?, NOW());";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, fullname);
            preparedStatement.setInt(4, departmentId);
            preparedStatement.setInt(5,positionID);

            int c = preparedStatement.executeUpdate();

            if (c > 0) {
                System.out.println("Them account thanh cong!");
            } else {
                System.out.println("Them account that bai!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeConnection();
        }

    }

    @Override
    public void xoaAccTheoUsername() {
        System.out.println("==== XOA ACC THEO USERNAME ====");
        System.out.print("Nhap username can xoa: ");
        String username = sc.nextLine();

        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM account WHERE username like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);

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
    public void updateFullname_theoUser() {
        System.out.println("==== CAP NHAT THEO USERNAME ====");
        System.out.print("Nhap username can cap nhat: ");
        String username = sc.nextLine();

        System.out.print("Nhap fullname can update: ");
        String fullname = sc.nextLine();

        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "update account SET full_name = ? where username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, fullname);
            preparedStatement.setString(2, username);

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
