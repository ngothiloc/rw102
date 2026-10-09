package backend.controller;

import backend.service.IAccountService;
import backend.service.impl.AccountServiceImpl;
import entity.Account;

import java.util.List;

public class AccountController {

    private IAccountService accountService;

    public AccountController(){
        accountService = new AccountServiceImpl();
    }

    public List<Account> hienThiToanBo() {
        return accountService.hienThiToanBo();
    }

    public List<Account> timKiemAccount_TheoUser(String ten) {
        return accountService.timKiemAccount_TheoUser(ten);
    }

    public boolean xoaAccTheoUsername(String username) {
        return accountService.xoaAccTheoUsername(username);
    }

    public boolean updateFullname_theoUser(String username, String fullname) {
        return accountService.updateFullname_theoUser(username, fullname);
    }

    public boolean existByUsername(String username) {
        return accountService.existByUsername(username);
    }

    public boolean existByEmail(String email) {
        return accountService.existByEmail(email);
    }

    public boolean existByName(String fullname) {
        return accountService.existByName(fullname);
    }

    public boolean existById(String accId) {
        return accountService.existById(accId);
    }

    public boolean xoaAccTheoId(String accId) {
        return accountService.xoaAccTheoId(accId);
    }

    public boolean updateFullname_theoAccId(String accId, String fullname) {
        return accountService.updateFullname_theoAccId(accId, fullname);
    }
}
