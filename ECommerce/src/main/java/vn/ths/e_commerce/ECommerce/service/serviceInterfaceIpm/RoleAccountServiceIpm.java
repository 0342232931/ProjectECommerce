package vn.ths.e_commerce.ECommerce.service.serviceInterfaceIpm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ths.e_commerce.ECommerce.entity.RoleAccount;
import vn.ths.e_commerce.ECommerce.repository.RoleAccountRepository;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.RoleAccountService;

import java.util.List;
import java.util.Optional;

@Service
public class RoleAccountServiceIpm implements RoleAccountService {
    private RoleAccountRepository roleAccountRepository;

    @Autowired
    public RoleAccountServiceIpm(RoleAccountRepository roleAccountRepository) {
        this.roleAccountRepository = roleAccountRepository;
    }

    @Override
    @Transactional
    public RoleAccount add(RoleAccount roleAccount) {
        return roleAccountRepository.saveAndFlush(roleAccount);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        roleAccountRepository.deleteById(id);
    }

    @Override
    public List<RoleAccount> getAll() {
        return roleAccountRepository.findAll();
    }

    @Override
    @Transactional
    public RoleAccount update(RoleAccount roleAccount) {
        Optional<RoleAccount> optionalRoleAccount = roleAccountRepository.findById(roleAccount.getId());
        if(optionalRoleAccount.isPresent()){
            return roleAccountRepository.saveAndFlush(roleAccount);
        }
        return null;
    }

    @Override
    public RoleAccount getById(Integer id) {
        Optional<RoleAccount> optionalRoleAccount = roleAccountRepository.findById(id);
        if(optionalRoleAccount.isPresent()){
            return roleAccountRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public RoleAccount getByRoleName(String name) {
        return roleAccountRepository.findRoleAccountByRoleName(name);
    }

    @Override
    public List<RoleAccount> updateAll(List<RoleAccount> roleAccounts) {
        return roleAccountRepository.saveAllAndFlush(roleAccounts);
    }
}
