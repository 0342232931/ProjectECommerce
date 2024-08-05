package vn.ths.e_commerce.ECommerce.service.serviceInterface;

import vn.ths.e_commerce.ECommerce.entity.RoleAccount;

import java.util.List;

public interface RoleAccountService {
    public RoleAccount add(RoleAccount roleAccount);
    public void deleteById(Integer id);
    public List<RoleAccount> getAll();
    public RoleAccount update(RoleAccount roleAccount);
    public RoleAccount getById(Integer id);
    public RoleAccount getByRoleName(String name);
    public List<RoleAccount> updateAll(List<RoleAccount> roleAccounts);
}
