package backend.repository;

import entity.Account;

import java.util.List;

public interface IAccountRepository {
    List<Account> hienThiToanBo();

    List<Account> timKiemAccount_TheoUser(String ten);

    boolean xoaAccTheoUsername(String username);
}
