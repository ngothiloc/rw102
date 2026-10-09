package backend.repository.impl;

import backend.repository.IAccountRepository;
import entity.Account;
import entity.Department;
import entity.Position;
import utils.JDBCUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements IAccountRepository {
    @Override
    public List<Account> hienThiToanBo() {
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
        return accountList;
    }

    @Override
    public List<Account> timKiemAccount_TheoUser(String ten) {
        List<Account> accountList = new ArrayList<>();
        Connection connection = JDBCUtils.getConnection();
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
        return accountList;
    }

    @Override
    public boolean xoaAccTheoUsername(String username) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM account WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);

            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeConnection();
        }
        return false;
    }

    @Override
    public boolean updateFullname_theoUser(String username, String fullname) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "update account SET full_name = ? where username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, fullname);
            preparedStatement.setString(2, username);

            int c = preparedStatement.executeUpdate();
            if(c > 0 ) return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeConnection();
        }
        return false;
    }

    @Override
    public boolean existByUsername(String username) {
        try {
            // tạo kết nối đến Database
            Connection connection = JDBCUtils.getConnection();
            String sql = "select * from account where username like ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {// next dc là có dữ liệu  -> tòn tại
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {// cụm này luôn thực hien cuối cùng
            JDBCUtils.closeConnection();
        }
        return false;
    }

    @Override
    public boolean existByEmail(String email) {
        try {
            // tạo kết nối đến Database
            Connection connection = JDBCUtils.getConnection();
            String sql = "select * from account where email like ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {// next dc là có dữ liệu  -> tòn tại
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {// cụm này luôn thực hien cuối cùng
            JDBCUtils.closeConnection();
        }
        return false;
    }

    @Override
    public boolean existByName(String fullname) {
        try {
            // tạo kết nối đến Database
            Connection connection = JDBCUtils.getConnection();
            String sql = "select * from account where full_name like ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, fullname);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {// next dc là có dữ liệu  -> tòn tại
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {// cụm này luôn thực hien cuối cùng
            JDBCUtils.closeConnection();
        }
        return false;
    }

    @Override
    public boolean existById(String accId) {
        try {
            // tạo kết nối đến Database
            Connection connection = JDBCUtils.getConnection();
            String sql = "select * from account where account_id like ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, accId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {// next dc là có dữ liệu  -> tòn tại
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {// cụm này luôn thực hien cuối cùng
            JDBCUtils.closeConnection();
        }
        return false;
    }

    @Override
    public boolean xoaAccTheoId(String accId) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM account WHERE account_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, accId);

            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeConnection();
        }
        return false;
    }

    @Override
    public boolean updateFullname_theoAccId(String accId, String fullname) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "update account SET full_name = ? where account_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, fullname);
            preparedStatement.setString(2, accId);

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