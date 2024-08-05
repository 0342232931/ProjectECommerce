package vn.ths.e_commerce.ECommerce.controller.AdminController.ProductController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.ths.e_commerce.ECommerce.entity.*;
import vn.ths.e_commerce.ECommerce.entity.Keyboard;
import vn.ths.e_commerce.ECommerce.entity.KeyboardDetail;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.KeyboardDetailService;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.KeyboardService;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class KeyboardController {
    private KeyboardService keyboardService;
    private KeyboardDetailService keyboardDetailService;
    private DataSource dataSource;

    @Autowired
    public KeyboardController(KeyboardService keyboardService, KeyboardDetailService keyboardDetailService,DataSource dataSource) {
        this.keyboardService = keyboardService;
        this.keyboardDetailService = keyboardDetailService;
        this.dataSource = dataSource;
    }

    @GetMapping("/listKeyboard")
    public String adminViewKeyboard(Model model){
        List<Keyboard> keyboards = keyboardService.getAll();
        model.addAttribute("keyboards", keyboards);
        return "/admin_view/list-keyboard";
    }

    @GetMapping("/displayImageKeyboard")
    public ResponseEntity<byte[]> displayImage(@RequestParam("id") int id) throws IOException, SQLException {
        Keyboard keyboard = keyboardService.getById(id);
        if(keyboard == null || keyboard.getImage() == null){
            return ResponseEntity.notFound().build();
        }
        byte [] imageBytes = null;
        imageBytes = keyboard.getImage().getBytes(1,(int) keyboard.getImage().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    public List<Integer> getKeyboardId (List<Keyboard> keyboards){
        List<Integer> keyboardId = new ArrayList<>();
        for (Keyboard keyboard : keyboards){
            if(keyboard.getKeyboardDetail()==null)
                keyboardId.add(keyboard.getId());
        }
        return keyboardId;
    }

    @GetMapping("/createKeyboard")
    public String adminCreateKeyboard(Model model){
        Keyboard keyboard = new Keyboard();
        KeyboardDetail keyboardDetail = new KeyboardDetail();
        List<Keyboard> keyboards = keyboardService.getAll();
        List<Integer> keyboardId = getKeyboardId(keyboards);
        model.addAttribute("keyboardId",keyboardId);
        model.addAttribute("keyboard",keyboard);
        model.addAttribute("keyboardDetail",keyboardDetail);
        return "/admin_view/create-keyboard";
    }

    @PostMapping("/saveKeyboard")
    public String adminSaveKeyboard(@ModelAttribute("keyboard") Keyboard keyboard, @RequestParam("title") String title,
                                  @RequestParam("type")String type, @RequestParam("photo") MultipartFile image, Model model){
        keyboard.setTitle(title);
        keyboard.setType(type);
        keyboard.setImage(null);
        // Add Image
        try {
            byte[] imageByteArray = image.getBytes();
            try {
                Connection connection = dataSource.getConnection();
                Blob avatar = connection.createBlob();
                avatar.setBytes(1,imageByteArray);
                keyboard.setImage(avatar);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            model.addAttribute("Error", "Lỗi khi lưu ảnh: " + e.getMessage());
            return "redirect:/admin/createKeyboard";
        }
        keyboardService.add(keyboard);
        return "redirect:/admin/createKeyboard";
    }

    @PostMapping("/saveKeyboardDetail")
    public String adminSaveKeyboard2(@ModelAttribute("keyboardDetail") KeyboardDetail keyboardDetail,@RequestParam("idForKeyboard")Integer id){
        Keyboard keyboard = keyboardService.getById(id);
        keyboardDetail.setKeyboard(keyboard);
        keyboardDetail.setTitle(keyboard.getTitle());
        keyboard.setKeyboardDetail(keyboardDetail);
        keyboardService.update(keyboard);
        keyboardDetailService.add(keyboardDetail);
        return "redirect:/admin/listKeyboard";
    }

    @GetMapping("/updateKeyboard")
    public String adminUpdateKeyboard(@RequestParam("keyboard_id") Integer id,Model model){
        Keyboard keyboard = keyboardService.getById(id);
        KeyboardDetail keyboardDetail = keyboard.getKeyboardDetail();
        if (keyboardDetail != null){
            model.addAttribute("keyboardId",keyboard.getId());
            model.addAttribute("keyboard",keyboard);
            model.addAttribute("keyboardDetail",keyboardDetail);
            return "/admin_view/create-keyboard";
        }
        return "redirect:/admin/listKeyboard";
    }

    @GetMapping("/deleteKeyboard")
    public String adminDeleteKeyboard(@RequestParam("keyboard_id") Integer id){
        keyboardService.deleteById(id);
        return "redirect:/admin/listKeyboard";
    }
}