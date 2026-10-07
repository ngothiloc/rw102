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
}
