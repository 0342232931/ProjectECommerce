package vn.ths.e_commerce.ECommerce.controller;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import org.apache.tomcat.util.descriptor.web.ContextHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.ths.e_commerce.ECommerce.entity.*;
import vn.ths.e_commerce.ECommerce.repository.UserRepository;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.*;

import javax.naming.Context;
import java.util.List;

@Controller
@RequestMapping("")
public class HomeController {
    private LaptopService laptopService;
    private KeyboardService keyboardService;
    private MouseService mouseService;
    private ScreenService screenService;
    private LaptopDetailService laptopDetailService;
    private ScreenDetailService screenDetailService;
    private MouseDetailService mouseDetailService;
    private KeyboardDetailService keyboardDetailService;
    private AccountService accountService;
    private UserRepository userRepository;
    private CartService cartService;
    private RoleAccountService roleAccountService;

    @Autowired
    public HomeController(LaptopService laptopService, KeyboardService keyboardService, AccountService accountService,
                          MouseService mouseService, ScreenService screenService, LaptopDetailService laptopDetailService,
                          ScreenDetailService screenDetailService, MouseDetailService mouseDetailService,CartService cartService,
                          KeyboardDetailService keyboardDetailService,UserRepository userRepository,RoleAccountService roleAccountService ) {
        this.laptopService = laptopService;
        this.cartService = cartService;
        this.keyboardService = keyboardService;
        this.mouseService = mouseService;
        this.screenService = screenService;
        this.laptopDetailService = laptopDetailService;
        this.screenDetailService = screenDetailService;
        this.mouseDetailService = mouseDetailService;
        this.keyboardDetailService = keyboardDetailService;
        this.accountService = accountService;
        this.roleAccountService = roleAccountService;
        this.userRepository = userRepository;
    }

    // Điều hướng đến các trang
    @GetMapping("/aboutMe")
    public String AboutMe(){
        return "about-me";
    }

    // Lấy ra danh sách các sản phẩm để hiển thị ở trang home
    @GetMapping("/home")
    public String home(Model model,HttpSession session){
        List<Laptop> laptops = laptopService.getAll();
        List<Screen> screens = screenService.getAll();
        List<Keyboard> keyboards = keyboardService.getAll();
        List<Mouse> mouses = mouseService.getAll();
        if (session.getAttribute("account")!=null){
            Account account = (Account) session.getAttribute("account");
            if(account.getRoleAccount().equals("ROLE_USER")) {
                User user = userRepository.findUserByAccount(account);
                Cart cart = cartService.getByUser(user);
                int number = cart.getLaptop().size() + cart.getScreens().size()
                        + cart.getMouses().size() + cart.getKeyboards().size();
                model.addAttribute("countProductInCart", number);
            }
        }
        model.addAttribute("laptops", laptops);
        model.addAttribute("screens", screens);
        model.addAttribute("keyboards", keyboards);
        model.addAttribute("mouses", mouses);
        return "home";
    }

    @GetMapping("/login")
    public String loginform(){
        return "/login";
    }

    @GetMapping("")
    public String home2(){
        return "redirect:/home";
    }

    // Khi bấm vào sản phẩm ở trang home sẽ nhãy sang trang chứa detail của sản phẩm
    @GetMapping("/laptopDetail")
    public String detail(@RequestParam("id") Integer id,Model model){
        LaptopDetail laptopDetail = laptopDetailService.getById(id);
        model.addAttribute("laptopDetail",laptopDetail);
        return "/laptop-detail";
    }

    @GetMapping("/screenDetail")
    public String detail1(@RequestParam("id") Integer id,Model model){
        ScreenDetail screenDetail = screenDetailService.getById(id);
        model.addAttribute("screenDetail",screenDetail);
        return "/screen-detail";
    }

    @GetMapping("/keyboardDetail")
    public String detail3(@RequestParam("id") Integer id,Model model){
        KeyboardDetail keyboardDetail = keyboardDetailService.getById(id);
        model.addAttribute("keyboardDetail",keyboardDetail);
        return "/keyboard-detail";
    }

    @GetMapping("/mouseDetail")
    public String detail4(@RequestParam("id") Integer id,Model model){
        MouseDetail mouseDetail = mouseDetailService.getById(id);
        model.addAttribute("mouseDetail",mouseDetail);
        return "/mouse-detail";
    }
}
