package vn.ths.e_commerce.ECommerce.controller.AdminController.ProductController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.ths.e_commerce.ECommerce.entity.*;
import vn.ths.e_commerce.ECommerce.entity.Screen;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.CartService;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.ScreenDetailService;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.ScreenService;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ScreenController {
    private ScreenService screenService;
    private ScreenDetailService screenDetailService;
    private DataSource dataSource;

    @Autowired
    public ScreenController(ScreenService screenService, ScreenDetailService screenDetailService,DataSource dataSource) {
        this.screenService = screenService;
        this.screenDetailService = screenDetailService;
        this.dataSource = dataSource;
    }

    @GetMapping("/listScreen")
    public String admin_viewScreen(Model model){
        List<Screen> screens = screenService.getAll();
        model.addAttribute("screens", screens);
        return "/admin_view/list-screen";
    }

    @GetMapping("/displayImageScreen")
    public ResponseEntity<byte[]> displayImage(@RequestParam("id") int id) throws IOException, SQLException {
        Screen screen = screenService.getById(id);
        if(screen == null || screen.getImage() == null){
            return ResponseEntity.notFound().build();
        }
        byte [] imageBytes = null;
        imageBytes = screen.getImage().getBytes(1,(int) screen.getImage().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    public List<Integer> getScreenId (List<Screen> screens){
        List<Integer> screenId = new ArrayList<>();
        for (Screen screen : screens){
            if(screen.getScreenDetail()==null)
                screenId.add(screen.getId());
        }
        return screenId;
    }

    @GetMapping("/createScreen")
    public String adminCreateScreen(Model model){
        Screen screen = new Screen();
        ScreenDetail screenDetail = new ScreenDetail();
        List<Screen> screens = screenService.getAll();
        List<Integer> screenId = getScreenId(screens);
        model.addAttribute("screenId",screenId);
        model.addAttribute("screen",screen);
        model.addAttribute("screenDetail",screenDetail);
        return "/admin_view/create-screen";
    }

    @PostMapping("/saveScreen")
    public String adminSaveScreen(@ModelAttribute("screen") Screen screen, @RequestParam("title") String title,
                                  @RequestParam("type")String type, @RequestParam("photo") MultipartFile image, Model model){
        screen.setTitle(title);
        screen.setType(type);
        screen.setImage(null);
        // Add Image
        try {
            byte[] imageByteArray = image.getBytes();
            try {
                Connection connection = dataSource.getConnection();
                Blob avatar = connection.createBlob();
                avatar.setBytes(1,imageByteArray);
                screen.setImage(avatar);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            model.addAttribute("Error", "Lỗi khi lưu ảnh: " + e.getMessage());
            return "redirect:/admin/createScreen";
        }
        screenService.add(screen);
        return "redirect:/admin/createScreen";
    }

    @PostMapping("/saveScreenDetail")
    public String adminSaveScreen2(@ModelAttribute("screenDetail") ScreenDetail screenDetail,@RequestParam("idForScreen")Integer id){
        Screen screen = screenService.getById(id);
        screenDetail.setScreen(screen);
        screenDetail.setTitle(screen.getTitle());
        screen.setScreenDetail(screenDetail);
        screenService.update(screen);
        screenDetailService.add(screenDetail);
        return "redirect:/admin/listScreen";
    }

    @GetMapping("/updateScreen")
    public String adminUpdateScreen(@RequestParam("screen_id") Integer id,Model model){
        Screen screen = screenService.getById(id);
        ScreenDetail screenDetail = screen.getScreenDetail();
        if (screenDetail != null){
            model.addAttribute("screenId",screen.getId());
            model.addAttribute("screen",screen);
            model.addAttribute("screenDetail",screenDetail);
            return "/admin_view/create-screen";
        }
        return "redirect:/admin/listScreen";
    }

    @GetMapping("/deleteScreen")
    public String adminDeleteScreen(@RequestParam("screen_id") Integer id){
        screenService.deleteById(id);
        return "redirect:/admin/listScreen";
    }
}