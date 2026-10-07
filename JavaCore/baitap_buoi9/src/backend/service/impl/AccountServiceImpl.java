package backend.service.impl;

import backend.repository.IAccountRepository;
import backend.repository.impl.AccountRepositoryImpl;
import backend.service.IAccountService;
import entity.Account;

import java.util.List;

public class AccountServiceImpl implements IAccountService {
    private IAccountRepository accountRepository;
    public AccountServiceImpl(){
        accountRepository = new AccountRepositoryImpl();
    }

    @Override
    public List<Account> hienThiToanBo() {
        return accountRepository.hienThiToanBo();
    }

    @Override
    public List<Account> timKiemAccount_TheoUser(String ten) {
        return accountRepository.timKiemAccount_TheoUser(ten);
    }

    @Override
    public boolean xoaAccTheoUsername(String username) {
        return accountRepository.xoaAccTheoUsername(username);
    }
}
