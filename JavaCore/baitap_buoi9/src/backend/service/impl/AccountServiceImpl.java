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

    @Override
    public boolean updateFullname_theoUser(String username, String fullname) {
        return accountRepository.updateFullname_theoUser(username, fullname);
    }

    @Override
    public boolean existByUsername(String username) {
        return accountRepository.existByUsername(username);
    }

    @Override
    public boolean existByEmail(String email) {
        return accountRepository.existByEmail(email);
    }

    @Override
    public boolean existByName(String fullname) {
        return accountRepository.existByName(fullname);
    }

    @Override
    public boolean existById(String accId) {
        return accountRepository.existById(accId);
    }

    @Override
    public boolean xoaAccTheoId(String accId) {
        return accountRepository.xoaAccTheoId(accId);
    }

    @Override
    public boolean updateFullname_theoAccId(String accId, String fullname) {
        return accountRepository.updateFullname_theoAccId(accId, fullname);
    }
}
