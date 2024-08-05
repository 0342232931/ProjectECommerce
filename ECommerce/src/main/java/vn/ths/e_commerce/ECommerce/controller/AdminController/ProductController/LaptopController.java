package vn.ths.e_commerce.ECommerce.controller.AdminController.ProductController;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.validation.Valid;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.ths.e_commerce.ECommerce.entity.*;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.*;

import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class LaptopController {
    private LaptopService laptopService;
    private LaptopDetailService laptopDetailService;
    private DataSource dataSource;
    private SessionFactory emf;

    @Autowired
    public LaptopController(LaptopService laptopService, SessionFactory emf, LaptopDetailService laptopDetailService, DataSource dataSource) {
        this.laptopService = laptopService;
        this.laptopDetailService = laptopDetailService;
        this.dataSource = dataSource;
        this.emf = emf;
    }

    @GetMapping("/listLaptop")
    public String adminViewLaptop(Model model) {
        List<Laptop> laptops = laptopService.getAll();
        model.addAttribute("laptops", laptops);
        return "/admin_view/list-laptop";
    }

    @GetMapping("/displayImageLaptop")
    public ResponseEntity<byte[]> displayImage(@RequestParam("id") int id) throws IOException, SQLException {
        Laptop laptop = laptopService.getById(id);
        if (laptop == null || laptop.getImage() == null) {
            return ResponseEntity.notFound().build();
        }
        byte[] imageBytes = null;
        imageBytes = laptop.getImage().getBytes(1, (int) laptop.getImage().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }


    public List<Integer> GetLaptopIdHaveNotLaptopDetail(List<Laptop> laptops){
        List<Integer> laptopId = new ArrayList<>();
        for (Laptop laptop : laptops) {
            if(laptop.getLaptopDetail() == null)
                laptopId.add(laptop.getId());
        }
        return laptopId;
    }

    @GetMapping("/createLaptop")
    public String adminCreateLaptop(Model model) {
        Laptop laptop = new Laptop();
        LaptopDetail laptopDetail = new LaptopDetail();
        List<Laptop> laptops = laptopService.getAll();
        List<Integer> laptopId = GetLaptopIdHaveNotLaptopDetail(laptops);
        model.addAttribute("laptopId", laptopId);
        model.addAttribute("laptop", laptop);
        model.addAttribute("laptopDetail", laptopDetail);
        return "/admin_view/create-laptop";
    }

    @PostMapping("/saveLaptop")
    public String adminSaveLaptop(@ModelAttribute("laptop") Laptop laptop, @RequestParam("title") String title,
                                  @RequestParam("type") String type, @RequestParam("photo") MultipartFile image,
                                  Model model) throws IOException {
        // Add Image
        laptop.setImage(null);
        try {
            byte[] imageByteArray = image.getBytes();
            try {
                Connection connection = dataSource.getConnection();
                Blob avatar = connection.createBlob();
                avatar.setBytes(1, imageByteArray);
                // Lưu Blob vào đối tượng laptop
                laptop.setImage(avatar);
            } catch (Exception e) {
                model.addAttribute("error", "Lỗi khi lưu ảnh: " + e.getMessage());
                return "redirect:/admin/createLaptop";
            }
        } catch (IOException e) {
            model.addAttribute("error", "Lỗi khi lưu ảnh: " + e.getMessage());
            return "redirect:/admin/createLaptop";
        }

        laptop.setTitle(title);
        laptop.setType(type);
        laptopService.add(laptop);
        return "redirect:/admin/createLaptop";
    }

//@PostMapping("/saveLaptop")
//public String adminSaveLaptop(@ModelAttribute("laptop") Laptop laptop, @RequestParam(value = "title",required = false) String title,
//                              @RequestParam(value = "type",required = false) String type, @RequestPart(value = "photo",required = false) MultipartFile image,
//                              Model model,@ModelAttribute("laptopDetail") LaptopDetail laptopDetail) throws IOException {
//    // Add Image
//    laptop.setImage(null);
//    try {
//        byte[] imageByteArray = image.getBytes();
//        try {
//            Connection connection = dataSource.getConnection();
//            Blob avatar = connection.createBlob();
//            avatar.setBytes(1, imageByteArray);
//            // Lưu Blob vào đối tượng laptop
//            laptop.setImage(avatar);
//        } catch (Exception e) {
//            model.addAttribute("error", "Lỗi khi lưu ảnh: " + e.getMessage());
//            return "redirect:/admin/createLaptop";
//        }
//    } catch (IOException e) {
//        model.addAttribute("error", "Lỗi khi lưu ảnh: " + e.getMessage());
//        return "redirect:/admin/createLaptop";
//    }
//
//    laptop.setTitle(title);
//    laptop.setType(type);
//    laptopDetail.setTitle(title);
//    laptopDetail.setLaptop(laptop);
//    laptop.setLaptopDetail(laptopDetail);
//    laptopService.add(laptop);
//    laptopDetailService.add(laptopDetail);
//    return "redirect:/admin/createLaptop";
//}

    @PostMapping("/saveLaptopDetail")
    public String adminSaveLaptop2(@ModelAttribute("laptopDetail") LaptopDetail laptopDetail, @RequestParam("idForLaptop") Integer idForLaptop, Model model) {
        Laptop laptop = laptopService.getById(idForLaptop);
        laptopDetail.setLaptop(laptop);
        laptopDetail.setTitle(laptop.getTitle());
        laptop.setLaptopDetail(laptopDetail);
        laptopService.update(laptop);
        laptopDetailService.add(laptopDetail);
        return "redirect:/admin/createLaptop";
    }

    @GetMapping("/updateLaptop")
    public String adminUpdateLaptop(@RequestParam("laptop_id") Integer id, Model model) {
        Laptop laptop = laptopService.getById(id);
        LaptopDetail laptopDetail = laptop.getLaptopDetail();
        if (laptopDetail != null) {
            model.addAttribute("laptopDetailForLaptop",laptop.getLaptopDetail());
            model.addAttribute("laptopId", laptop.getId());
            model.addAttribute("laptop", laptop);
            model.addAttribute("laptopDetail", laptopDetail);
            return "/admin_view/create-laptop";
        }
        return "redirect:/admin/listLaptop";
    }

    @GetMapping("/deleteLaptop")
    public String adminDeleteLaptop(@RequestParam("laptop_id") Integer id) {
        laptopService.deleteById(id);
        return "redirect:/admin/listLaptop";
    }
}
