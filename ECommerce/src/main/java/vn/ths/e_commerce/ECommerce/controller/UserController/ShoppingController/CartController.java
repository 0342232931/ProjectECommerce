package vn.ths.e_commerce.ECommerce.controller.UserController.ShoppingController;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;
import vn.ths.e_commerce.ECommerce.entity.*;
import vn.ths.e_commerce.ECommerce.repository.AccountRepository;
import vn.ths.e_commerce.ECommerce.repository.RoleAccountRepository;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class CartController {
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
    public CartController(CartService cartService,AccountRepository accountRepository, LaptopService laptopService,AccountService accountService, MouseService mouseService,
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

    @GetMapping("/register")
    public String registerform(Model model){
        Account account = new Account();
        model.addAttribute("account",account);
        return "/register";
    }

    @InitBinder
    public void initBinder(WebDataBinder data){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        data.registerCustomEditor(String.class, stringTrimmerEditor);
    }


    @PostMapping("/saveAccount")
    public String registersave(@Valid @ModelAttribute("account") Account account, BindingResult result,
                               @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                               Model model){
        if(result.hasErrors()){
            return "redirect:/user/register";
        }
        RoleAccount roleAccount = repository.findRoleAccountByRoleName("ROLE_USER");
        account.setRoleAccount(roleAccount);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Account accountExiting = accountRepository.findByUserName(account.getUserName());
        if (accountExiting != null){
            model.addAttribute("error","Tài Khoản Đã Tồn Tại");
            return "/register";
        }
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        Cart cart = new Cart();
        Purchase purchase = new Purchase();
        user.setPurchase(purchase);
        user.setCart(cart);
        Account accountFinal = new Account();
        accountFinal.setRoleAccount(roleAccount);
        accountFinal.setUserName(account.getUserName());
        accountFinal.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        accountFinal.setUser(user);
        userService.add(user);
        cartService.add(cart);
        accountService.add(accountFinal);
        return "redirect:/login";
    }

    @GetMapping("/addProductToCart")
    public String AddProduct(@RequestParam("id")Integer id, @RequestParam("type") String title,Model model){
        String lowerCase = title.toLowerCase();
        switch (lowerCase){
            case "laptop" : {
                Laptop laptop = laptopService.getById(id);
                String userName = SecurityContextHolder.getContext().getAuthentication().getName();
                Account account = accountRepository.findByUserName(userName);
                User user = account.getUser();
                Cart cart = user.getCart();
                List<Laptop> laptops = cart.getLaptop();
                laptops.add(laptop);
                cart.setLaptop(laptops);
                user.setCart(cart);
                cartService.update(cart);
                userService.update(user);
                model.addAttribute("laptop", laptop);
                model.addAttribute("laptopDetail",laptop.getLaptopDetail());
                return "/laptop-detail";
            }
            case "mouse" : {
                Mouse mouse = mouseService.getById(id);
                String userName = SecurityContextHolder.getContext().getAuthentication().getName();
                Account account = accountRepository.findByUserName(userName);
                User user = account.getUser();
                Cart cart = user.getCart();
                List<Mouse> mouses = cart.getMouses();
                mouses.add(mouse);
                cart.setMouses(mouses);
                user.setCart(cart);
                cartService.update(cart);
                userService.update(user);
                model.addAttribute("mouse",mouse);
                model.addAttribute("mouseDetail",mouse.getMouseDetail());
                return "/mouse-detail";
            }
            case "keyboard" : {
                Keyboard keyboard = keyboardService.getById(id);
                String userName = SecurityContextHolder.getContext().getAuthentication().getName();
                Account account = accountRepository.findByUserName(userName);
                User user = account.getUser();
                Cart cart = user.getCart();
                List<Keyboard> keyboards = cart.getKeyboards();
                keyboards.add(keyboard);
                cart.setKeyboards(keyboards);
                user.setCart(cart);
                cartService.update(cart);
                userService.update(user);
                model.addAttribute("keyboard",keyboard);
                model.addAttribute("keyboardDetail",keyboard.getKeyboardDetail());
                return "/keyboard-detail";
            }
            case "screen" : {
                Screen screen = screenService.getById(id);
                String userName = SecurityContextHolder.getContext().getAuthentication().getName();
                Account account = accountRepository.findByUserName(userName);
                User user = account.getUser();
                Cart cart = user.getCart();
                List<Screen> screens = cart.getScreens();
                screens.add(screen);
                cart.setScreens(screens);
                user.setCart(cart);
                cartService.update(cart);
                userService.update(user);
                model.addAttribute("screen",screen);
                model.addAttribute("screenDetail",screen.getScreenDetail());
                return "/screen-detail";
            }
            default:{
                return "redirect:/home";
            }
        }
    }

    @GetMapping("/showCart")
    public String showCart(Model model){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByUserName(userName);
        User user = account.getUser();
        Cart cart = user.getCart();
        List<Laptop> laptops = cart.getLaptop();
        List<Mouse> mouseList = cart.getMouses();
        List<Screen> screens = cart.getScreens();
        List<Keyboard> keyboards = cart.getKeyboards();
        model.addAttribute("laptops",laptops);
        model.addAttribute("mouseList",mouseList);
        model.addAttribute("screens",screens);
        model.addAttribute("keyboards",keyboards);
        return "/show-cart";
    }

    @GetMapping("/deleteProductInCart")
    public String deleteProductInCart(@RequestParam("id") Integer id, @RequestParam("type") String title){
        String lowerCase = title.toLowerCase();
        switch (lowerCase){
            case "laptop" : {
                Laptop laptop = laptopService.getById(id);
                String userName = SecurityContextHolder.getContext().getAuthentication().getName();
                Account account = accountRepository.findByUserName(userName);
                User user = account.getUser();
                Cart cart = user.getCart();
                List<Laptop> laptops = cart.getLaptop();
                for(Laptop laptop1 : laptops){
                    if (laptop1.equals(laptop)){
                        laptops.remove(laptop1);
                        break;
                    }
                }
                cart.setLaptop(laptops);
                user.setCart(cart);
                cartService.update(cart);
                userService.update(user);
                return "redirect:/user/showCart";
            }
            case "mouse" : {
                Mouse mouse = mouseService.getById(id);
                String userName = SecurityContextHolder.getContext().getAuthentication().getName();
                Account account = accountRepository.findByUserName(userName);
                User user = account.getUser();
                Cart cart = user.getCart();
                List<Mouse> mouses = cart.getMouses();
                for(Mouse mouse1 : mouses){
                    if (mouse1.equals(mouse)){
                        mouses.remove(mouse1);
                        break;
                    }
                }
                cart.setMouses(mouses);
                user.setCart(cart);
                cartService.update(cart);
                userService.update(user);
                return "redirect:/user/showCart";
            }
            case "keyboard" : {
                Keyboard keyboard = keyboardService.getById(id);
                String userName = SecurityContextHolder.getContext().getAuthentication().getName();
                Account account = accountRepository.findByUserName(userName);
                User user = account.getUser();
                Cart cart = user.getCart();
                List<Keyboard> keyboards = cart.getKeyboards();
                for(Keyboard keyboard1 : keyboards){
                    if (keyboard1.equals(keyboard)){
                        keyboards.remove(keyboard1);
                        break;
                    }
                }
                cart.setKeyboards(keyboards);
                user.setCart(cart);
                cartService.update(cart);
                userService.update(user);
                return "redirect:/user/showCart";
            }
            case "screen" : {
                Screen screen = screenService.getById(id);
                String userName = SecurityContextHolder.getContext().getAuthentication().getName();
                Account account = accountRepository.findByUserName(userName);
                User user = account.getUser();
                Cart cart = user.getCart();
                List<Screen> screens = cart.getScreens();
                for(Screen screen1 : screens){
                    if (screen1.equals(screen)){
                        screens.remove(screen1);
                        break;
                    }
                }
                cart.setScreens(screens);
                user.setCart(cart);
                cartService.update(cart);
                userService.update(user);
                return "redirect:/user/showCart";
            }
            default:{
                return "redirect:/user/showCart";
            }
        }
    }
}
