package vn.ths.e_commerce.ECommerce.controller.AdminController.ProductController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.ths.e_commerce.ECommerce.entity.*;
import vn.ths.e_commerce.ECommerce.entity.Mouse;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class MouseController {
    private MouseService mouseService;
    private MouseDetailService mouseDetailService;
    private DataSource dataSource;

    @Autowired
    public MouseController(MouseService mouseService, MouseDetailService mouseDetailService,DataSource dataSource) {
        this.mouseService = mouseService;
        this.mouseDetailService = mouseDetailService;
        this.dataSource = dataSource;
    }

    @GetMapping("/listMouse")
    public String admin_viewMouse(Model model){
        List<Mouse> mouses = mouseService.getAll();
        model.addAttribute("mouses", mouses);
        return "/admin_view/list-mouse";
    }

    @GetMapping("/displayImageMouse")
    public ResponseEntity<byte[]> displayImage(@RequestParam("id") int id) throws IOException, SQLException {
        Mouse mouse = mouseService.getById(id);
        if(mouse == null || mouse.getImage() == null){
            return ResponseEntity.notFound().build();
        }
        byte [] imageBytes = null;
        imageBytes = mouse.getImage().getBytes(1,(int) mouse.getImage().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    public List<Integer> getMouseId (List<Mouse> mouses){
        List<Integer> mouseId = new ArrayList<>();
        for (Mouse mouse : mouses){
            if(mouse.getMouseDetail()==null)
                mouseId.add(mouse.getId());
        }
        return mouseId;
    }

    @GetMapping("/createMouse")
    public String adminCreateMouse(Model model){
        Mouse mouse = new Mouse();
        MouseDetail mouseDetail = new MouseDetail();
        List<Mouse> mouses = mouseService.getAll();
        List<Integer> mouseId = getMouseId(mouses);
        model.addAttribute("mouseId",mouseId);
        model.addAttribute("mouse",mouse);
        model.addAttribute("mouseDetail",mouseDetail);
        return "/admin_view/create-mouse";
    }

    @PostMapping("/saveMouse")
    public String adminSaveMouse(@ModelAttribute("mouse") Mouse mouse, @RequestParam("title") String title,
                                  @RequestParam("type")String type, @RequestParam("photo") MultipartFile image, Model model){
        mouse.setTitle(title);
        mouse.setType(type);
        mouse.setImage(null);
        // Add Image
        try {
            byte[] imageByteArray = image.getBytes();
            try {
                Connection connection = dataSource.getConnection();
                Blob avatar = connection.createBlob();
                avatar.setBytes(1,imageByteArray);
                mouse.setImage(avatar);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            model.addAttribute("Error", "Lỗi khi lưu ảnh: " + e.getMessage());
            return "redirect:/admin/createMouse";
        }
        mouseService.add(mouse);
        return "redirect:/admin/createMouse";
    }

    @PostMapping("/saveMouseDetail")
    public String adminSaveMouse2(@ModelAttribute("mouseDetail") MouseDetail mouseDetail,@RequestParam("idForMouse")Integer id){
        Mouse mouse = mouseService.getById(id);
        mouseDetail.setMouse(mouse);
        mouseDetail.setTitle(mouse.getTitle());
        mouse.setMouseDetail(mouseDetail);
        mouseService.update(mouse);
        mouseDetailService.add(mouseDetail);
        return "redirect:/admin/createMouse";
    }

    @GetMapping("/updateMouse")
    public String adminUpdateMouse(@RequestParam("mouse_id") Integer id,Model model){
        Mouse mouse = mouseService.getById(id);
        MouseDetail mouseDetail = mouse.getMouseDetail();
        if (mouseDetail != null){
            model.addAttribute("mouseId",mouse.getId());
            model.addAttribute("mouse",mouse);
            model.addAttribute("mouseDetail",mouseDetail);
            return "/admin_view/create-mouse";
        }
        return "redirect:/admin/listMouse";
    }

    @GetMapping("/deleteMouse")
    public String adminDeleteMouse(@RequestParam("mouse_id") Integer id){
        mouseService.deleteById(id);
        return "redirect:/admin/listMouse";
    }
}