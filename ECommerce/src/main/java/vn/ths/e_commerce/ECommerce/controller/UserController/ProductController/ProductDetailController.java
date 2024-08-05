package vn.ths.e_commerce.ECommerce.controller.UserController.ProductController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.ths.e_commerce.ECommerce.entity.*;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.*;

@Controller
@RequestMapping("/detail")
public class ProductDetailController {
    private LaptopService laptopService;
    private ScreenService screenService;
    private MouseService mouseService;
    private KeyboardService keyboardService;

    @Autowired
    public ProductDetailController(LaptopService laptopService, ScreenService screenService, MouseService mouseService, KeyboardService keyboardService) {
        this.laptopService = laptopService;
        this.screenService = screenService;
        this.mouseService = mouseService;
        this.keyboardService = keyboardService;
    }

    @GetMapping("/laptopDetail")
    public String getDetail(@RequestParam("id") Integer id, Model model){
        Laptop laptop = laptopService.getById(id);
        LaptopDetail laptopDetail = laptop.getLaptopDetail();
        model.addAttribute("laptop",laptop);
        model.addAttribute("laptopDetail",laptopDetail);
        return "/laptop-detail";
    }

    @GetMapping("/screenDetail")
    public String getDetail1(@RequestParam("id") Integer id, Model model){
        Screen screen = screenService.getById(id);
        ScreenDetail screenDetail = screen.getScreenDetail();
        model.addAttribute("screen",screen);
        model.addAttribute("screenDetail",screenDetail);
        return "/screen-detail";
    }

    @GetMapping("/mouseDetail")
    public String getDetail2(@RequestParam("id") Integer id, Model model){
        Mouse mouse = mouseService.getById(id);
        MouseDetail mouseDetail = mouse.getMouseDetail();
        model.addAttribute("mouse",mouse);
        model.addAttribute("mouseDetail",mouseDetail);
        return "/mouse-detail";
    }

    @GetMapping("/keyboardDetail")
    public String getDetail3(@RequestParam("id") Integer id, Model model){
        Keyboard keyboard = keyboardService.getById(id);
        KeyboardDetail keyboardDetail = keyboard.getKeyboardDetail();
        model.addAttribute("keyboard",keyboard);
        model.addAttribute("keyboardDetail",keyboardDetail);
        return "/keyboard-detail";
    }
}
