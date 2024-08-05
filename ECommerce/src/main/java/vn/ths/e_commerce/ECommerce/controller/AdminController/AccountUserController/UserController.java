package vn.ths.e_commerce.ECommerce.controller.AdminController.AccountUserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.ths.e_commerce.ECommerce.entity.User;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/listUser")
    public String listUser(Model model){
        List<User> users = userService.getAll();
        model.addAttribute("users",users);
        return "/admin_view/list-user";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") Integer id){
        userService.deleteById(id);
        return "redirect:/admin/listUser";
    }
}
