package vn.ths.e_commerce.ECommerce.service.serviceInterfaceIpm;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ths.e_commerce.ECommerce.entity.Account;
import vn.ths.e_commerce.ECommerce.entity.RoleAccount;
import vn.ths.e_commerce.ECommerce.repository.AccountRepository;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.AccountRegisterService;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.AccountService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceIpm implements AccountService, AccountRegisterService {
    private AccountRepository accountRepository;
    private HttpSession session;

    @Autowired
    public AccountServiceIpm(AccountRepository accountRepository,HttpSession session) {
        this.accountRepository = accountRepository;
        this.session = session;
    }

    @Override
    @Transactional
    public Account add(Account account) {
        return accountRepository.save(account);

    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional
    public Account update(Account account) {
        Optional<Account> optionalAccount = accountRepository.findById(account.getId());
        if(optionalAccount.isPresent()){
            return accountRepository.saveAndFlush(account);
        } else {
            return null;
        }
    }

    @Override
    public Account getById(Integer id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if(optionalAccount.isPresent()){
            return accountRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public Account findByUserName(String username){
        return accountRepository.findByUserName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserName(username);
        if (account == null){
            throw new UsernameNotFoundException("Tài Khoản Hoặc Mật Khẩu Không Chính Xác");
        }
        session.setAttribute("account",account);
        Account account1 = accountRepository.findAccountByIdJoinFetch(account.getId());
        return new User(account1.getUserName(),account1.getPassword(), Collections.singleton(roleToAuthorities(account1.getRoleAccount())));
    }

    private GrantedAuthority roleToAuthorities(RoleAccount role){
        return new SimpleGrantedAuthority(role.getRoleName());
    }
}
