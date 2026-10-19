package backend.service.impl;

import backend.repository.IAccountRepository;
import backend.repository.impl.AccountRepositoryImpl;
import backend.service.IAccountService;
import entity.Account;

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
}
