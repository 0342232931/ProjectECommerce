package vn.ths.e_commerce.ECommerce.controller.UserController.ProductController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.ths.e_commerce.ECommerce.entity.Keyboard;
import vn.ths.e_commerce.ECommerce.entity.Laptop;
import vn.ths.e_commerce.ECommerce.entity.Mouse;
import vn.ths.e_commerce.ECommerce.entity.Screen;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.KeyboardService;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.LaptopService;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.MouseService;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.ScreenService;

import java.util.List;

@Controller
@RequestMapping("/allProduct")
public class AllProductController {
    private LaptopService laptopService;
    private ScreenService screenService;
    private MouseService mouseService;
    private KeyboardService keyboardService;

    @Autowired
    public AllProductController(LaptopService laptopService, ScreenService screenService,
                                MouseService mouseService, KeyboardService keyboardService) {
        this.laptopService = laptopService;
        this.screenService = screenService;
        this.mouseService = mouseService;
        this.keyboardService = keyboardService;
    }

    @GetMapping("/listLaptop")
    public String adminViewLaptop(Model model){
        List<Laptop> laptops = laptopService.getAll();
        model.addAttribute("laptops", laptops);
        return "/all-laptop";
    }

    @GetMapping("/listKeyboard")
    public String adminViewKeyboard(Model model){
        List<Keyboard> keyboards = keyboardService.getAll();
        model.addAttribute("keyboards", keyboards);
        return "/all-keyboard";
    }

    @GetMapping("/listMouse")
    public String admin_viewMouse(Model model){
        List<Mouse> mouses = mouseService.getAll();
        model.addAttribute("mouses", mouses);
        return "/all-mouse";
    }

    @GetMapping("/listScreen")
    public String admin_viewScreen(Model model){
        List<Screen> screens = screenService.getAll();
        model.addAttribute("screens", screens);
        return "/all-screen";
    }
}
