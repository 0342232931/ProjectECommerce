package vn.ths.e_commerce.ECommerce.controller.AdminController.AccountUserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.ths.e_commerce.ECommerce.entity.Account;
import vn.ths.e_commerce.ECommerce.entity.RoleAccount;
import vn.ths.e_commerce.ECommerce.repository.RoleAccountRepository;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.AccountService;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.RoleAccountService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AccountController {
    private AccountService accountService;
    private RoleAccountService roleAccountService;
    private RoleAccountRepository repository;

    @Autowired
    public AccountController(AccountService accountService, RoleAccountService roleAccountService, RoleAccountRepository repository) {
        this.accountService = accountService;
        this.roleAccountService = roleAccountService;
        this.repository = repository;
    }

    @GetMapping("/listAccount")
    public String listAccount(Model model){
        List<Account> accounts = accountService.getAll();
        model.addAttribute("accounts",accounts);
        return "/admin_view/list-account";
    }

    public List<Account> removeElementInListAccount(List<Account> accounts,Account account){
        for (Account account1 : accounts){
            if (account1.equals(account)){
                accounts.remove(account1);
                break;
            }
        }
        return accounts;
    }

    public List<RoleAccount> RemoveElementInListRoleAccount(List<RoleAccount> roleAccounts,RoleAccount roleAccount,Account account){
        int length = roleAccounts.size();
        for (int i = 0; i< length;i++){
            if(roleAccounts.get(i).equals(roleAccount)){
                List<Account> accounts = roleAccounts.get(i).getAccount();
                List<Account> accounts1 = removeElementInListAccount(accounts,account);
                roleAccounts.get(i).setAccount(accounts1);
                break;
            }
        }
        return roleAccounts;
    }

    @GetMapping("/deleteAccount")
    public String deleteAccount(@RequestParam("id") Integer id, Model model){
        Account account = accountService.getById(id);
        RoleAccount roleAccount = account.getRoleAccount();
        account.setRoleAccount(null);
        List<RoleAccount> roleAccounts = roleAccountService.getAll();
        List<RoleAccount> roleAccountNew = RemoveElementInListRoleAccount(roleAccounts,roleAccount,account);
        repository.saveAllAndFlush(roleAccountNew);
        accountService.deleteById(id);
        return "redirect:/admin/listAccount";
    }
}
