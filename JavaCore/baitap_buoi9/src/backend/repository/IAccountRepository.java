package backend.repository;

import entity.Account;

import java.util.List;

public interface IAccountRepository {
    List<Account> hienThiToanBo();

    List<Account> timKiemAccount_TheoUser(String ten);

    boolean xoaAccTheoUsername(String username);

    boolean updateFullname_theoUser(String username, String fullname);

    boolean existByUsername(String username);

    boolean existByEmail(String email);

    boolean existByName(String fullname);

    boolean existById(String accId);

    boolean xoaAccTheoId(String accId);

    boolean updateFullname_theoAccId(String accId, String fullname);
}
