package backend.service;

import entity.Account;

import java.util.List;

public interface IAccountService {
    List<Account> hienThiToanBo();

    List<Account> timKiemAccount_TheoUser(String ten);

    boolean xoaAccTheoUsername(String username);
}
