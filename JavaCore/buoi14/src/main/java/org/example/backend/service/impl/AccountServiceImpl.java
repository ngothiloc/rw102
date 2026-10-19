package org.example.backend.service.impl;

import org.example.backend.repository.IAccountRepository;
import org.example.backend.repository.impl.AccountRepositoryImpl;
import org.example.backend.service.IAccountService;
import org.example.entity.Account;
import org.example.entity.Department;
import org.example.entity.Position;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl implements IAccountService {

    private IAccountRepository accountRepository;

    public AccountServiceImpl() {
        this.accountRepository = new AccountRepositoryImpl();
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
            header = bufferedReader.readLine(); // doc dong dau tien (tieu de)

            // neu line doc ra co du lieu thi se phan tich du lieu de lay ra cac thong tin
            while ((line = bufferedReader.readLine()) != null){
                String [] arrs = line.split(",");
                String email = arrs[0];
                String username = arrs[1];
                String fullName = arrs[2];
                int departmentId = Integer.parseInt(arrs[3]);
                int positionId = Integer.parseInt(arrs[4]);

                Department department = new Department(departmentId);
                Position position = new Position(positionId);
                // chuyển dữ liệu trong file thành các dữ Liệu Liên quan đến department
                Account account = new Account( email, username, fullName, department, position);
                // gọi xuống repo để zuu vao DB
//                accountRepository.create(account);
                 accounts.add(account);
            }
            accountRepository.createAll(accounts);
            return "Import thanh cong";
        } catch (Exception e){
            return "Loi";
        }
    }
}
