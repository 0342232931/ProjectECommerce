package vn.ths.e_commerce.ECommerce.service.serviceInterface;

import vn.ths.e_commerce.ECommerce.entity.Account;

import java.util.List;

public interface AccountService {
    public Account add(Account account);
    public void deleteById(Integer id);
    public List<Account> getAll();
    public Account update(Account account);
    public Account getById(Integer id);
    public Account findByUserName(String username);
}
