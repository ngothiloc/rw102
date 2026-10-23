package org.example.backend.service.impl;

import org.example.backend.common.StringCommon;
import org.example.backend.repository.IAccountRepository;
import org.example.backend.repository.IDepartmentRepository;
import org.example.backend.repository.IPositionRepository;
import org.example.backend.repository.impl.AccountRepositoryImpl;
import org.example.backend.repository.impl.DepartmentRepositoryImpl;
import org.example.backend.repository.impl.PositionRepositoryImpl;
import org.example.backend.service.IAccountService;
import org.example.entity.Account;
import org.example.entity.Department;
import org.example.entity.Position;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountServiceImpl implements IAccountService {

    private IAccountRepository accountRepository;
    private IDepartmentRepository departmentRepository;
    private IPositionRepository positionRepository;

    public AccountServiceImpl() {
        this.accountRepository = new AccountRepositoryImpl();
        this.departmentRepository = new DepartmentRepositoryImpl();
        this.positionRepository = new PositionRepositoryImpl();
    }



    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public boolean create(Account account) {
        return accountRepository.create(account);
    }

    @Override
    public boolean update(int id, String username) {
        return accountRepository.update(id, username);
    }

    @Override
    public boolean delete(int id) {
        return accountRepository.delete(id);
    }

    @Override
    public boolean checkUsernameExists(String username, Integer id) {
        return accountRepository.checkUsernameExists(username, id);
    }

    @Override
    public boolean checkEmailExists(String email, Integer id) {
        return accountRepository.checkEmailExists(email, id);
    }

    @Override
    public boolean checkIdExists(int id) {
        return accountRepository.checkIdExists(id);
    }

    @Override
    public List<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public String importCSV(String path) {
        // Lay du lieu tu file theo đường dẫn ở path // kierm tra xem file nay có tồn tại ko,
        File file = new File(path);
        if (!path.endsWith(".csv")){
            return "File khong dung dinh dang !!";
        }

        if (!file.exists()) {
            return "file khong ton tai";
        }
        // đọc file
        String header = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            List<Account> accounts = new ArrayList<>();
            List<String> dataErrors = new ArrayList<>();
            header = bufferedReader.readLine(); // doc dong dau tien (tieu de)

            List<String> usernameInFile = new ArrayList<>();
            List<String> emailInFile = new ArrayList<>();

            List<Account> accountsInDB = accountRepository.findAll();
            Map<String, Account> mapByUsername = new HashMap<>();
            Map<String, Account> mapByEmail = new HashMap<>();
            for (Account account : accountsInDB){
                mapByEmail.put(account.getEmail(), account);
                mapByUsername.put(account.getUsername(), account);
            }

            // neu line doc ra co du lieu thi se phan tich du lieu de lay ra cac thong tin
            while ((line = bufferedReader.readLine()) != null){
                List<String> errors = new ArrayList<>();

                String [] arrs = line.split(",");
                String email = arrs[0];
                String username = arrs[1];
                String fullName = arrs[2];
                Integer departmentId = Integer.parseInt(arrs[3]);
                Integer positionId = Integer.parseInt(arrs[4]);

                //chuyen depID -> Department : findById
                Department department = departmentRepository.findById(departmentId);
                //chuyen posID -> Position : findById
                Position position = positionRepository.findById(positionId);

                //validate email
                if (email.isEmpty()){
                    errors.add("Email khong duoc de trong");
                } else {
                    if (!email.matches(StringCommon.EMAIL_REGEX)){
                        errors.add("Email khong dung dinhd ang");
                    }
                    if (mapByEmail.get(email) != null){
                        errors.add("Email da ton tai!!!");
                    }
                    if (emailInFile.contains(email)){
                        errors.add("Email bi trung trong file");
                    }
                }

                //validate username
                if (username.isEmpty()){
                    errors.add("username khong duoc de trong");
                } else {
                    if (username.length() < 5 || username.length() > 50){
                        errors.add("Username khong du dop dai >5 <50");
                    }
                    if (mapByUsername.get(username) != null){
                        errors.add("username da ton tai!!!");
                    }
                    if (usernameInFile.contains(username)){
                        errors.add("username bi trung trong file");
                    }
                }

                //validate fullname
                if (fullName.isEmpty()){
                    errors.add("Full name khong duoc de trong");
                }

                if (errors.isEmpty()){
                    // chuyển dữ liệu trong file thành các dữ Liệu Liên quan đến department
                    Account account = new Account( email, username, fullName, department, position);
                    // gọi xuống repo để zuu vao DB
                    //accountRepository.create(account);
                    accounts.add(account);
                    emailInFile.add(email);
                    usernameInFile.add(username);
                } else {
                    String rowError = line + ","  + String.join("-", errors);
                    dataErrors.add(rowError);
                }
            }
            if (!accounts.isEmpty()){
                accountRepository.createAll(accounts);
            }

            if (!dataErrors.isEmpty()){
                String pathErr = "/Users/macos/Desktop/rw102/JavaCore/CSV/ErrAccount.csv";
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
            return String.format("Import thanh cong %d , import that bai %d. %s", accounts.size(), dataErrors.size(), !dataErrors.isEmpty() ? "Da xuat ra file loi ErrAccount.csv" : "");
        } catch (Exception e){
            return "Loi";
        }
    }
}
