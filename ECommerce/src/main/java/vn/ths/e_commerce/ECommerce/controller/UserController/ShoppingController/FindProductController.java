package vn.ths.e_commerce.ECommerce.controller.UserController.ShoppingController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.ths.e_commerce.ECommerce.entity.*;
import vn.ths.e_commerce.ECommerce.repository.AccountRepository;
import vn.ths.e_commerce.ECommerce.repository.RoleAccountRepository;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class FindProductController {
    private CartService cartService;
    private LaptopService laptopService;
    private MouseService mouseService;
    private ScreenService screenService;
    private KeyboardService keyboardService;
    private UserService userService;
    private RoleAccountRepository repository;
    private AccountService accountService;
    private AccountRepository accountRepository;

    @Autowired
    public FindProductController(CartService cartService,AccountRepository accountRepository, LaptopService laptopService,AccountService accountService, MouseService mouseService,
                          ScreenService screenService, KeyboardService keyboardService, UserService userService, RoleAccountRepository repository) {
        this.cartService = cartService;
        this.laptopService = laptopService;
        this.mouseService = mouseService;
        this.screenService = screenService;
        this.keyboardService = keyboardService;
        this.userService = userService;
        this.repository = repository;
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }


    @GetMapping("/findProduct")
    public String AddProduct(@RequestParam("productName")String name, @RequestParam("type") String title, Model model){
        String lowerCase = title.toLowerCase();
        switch (lowerCase){
            case "laptop" : {
                List<Laptop> laptops = laptopService.getAll();
                for (Laptop laptop : laptops){
                    if(laptop.getLaptopName().equalsIgnoreCase(name)){
                        model.addAttribute("laptop",laptop);
                        model.addAttribute("laptopDetail",laptop.getLaptopDetail());
                        return "/laptop-detail";
                    }
                }
                return "redirect:/home";
            }
            case "mouse" : {
                List<Mouse> mouseList = mouseService.getAll();
                for (Mouse mouse : mouseList){
                    if(mouse.getMouseName().equalsIgnoreCase(name)){
                        model.addAttribute("mouse",mouse);
                        model.addAttribute("mouseDetail",mouse.getMouseDetail());
                        return "/mouse-detail";
                    }
                }
                return "redirect:/home";
            }
            case "keyboard" : {
                List<Keyboard> keyboards = keyboardService.getAll();
                for (Keyboard keyboard : keyboards){
                    if(keyboard.getKeyboardName().equalsIgnoreCase(name)){
                        model.addAttribute("keyboard",keyboard);
                        model.addAttribute("keyboardDetail",keyboard.getKeyboardDetail());
                        return "/keyboard-detail";
                    }
                }
                return "redirect:/home";
            }
            case "screen" : {
                List<Screen> screens = screenService.getAll();
                for (Screen screen : screens){
                    if(screen.getScreenName().equalsIgnoreCase(name)){
                        model.addAttribute("screen",screen);
                        model.addAttribute("screenDetail",screen.getScreenDetail());
                        return "/screen-detail";
                    }
                }
                return "redirect:/home";
            }
            default:{
                return "redirect:/home";
            }
        }
    }
}
