package vn.ths.e_commerce.ECommerce.controller.AdminController.AccountUserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.ths.e_commerce.ECommerce.entity.Voucher;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.VoucherService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class VoucherController {
    private VoucherService voucherService;

    @Autowired
    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping("/listVoucher")
    public String listVoucher(Model model){
        List<Voucher> vouchers  = voucherService.getAll();
        model.addAttribute("vouchers",vouchers);
        return "/admin_view/list-voucher";
    }

    @GetMapping("/createVoucher")
    public String createVoucher(Model model){
        Voucher voucher = new Voucher();
        model.addAttribute("voucher",voucher);
        return "/admin_view/create-voucher";
    }

    @PostMapping("/saveVoucher")
    public String saveVoucher(@ModelAttribute("voucher") Voucher voucher){
        voucherService.add(voucher);
        return "redirect:/admin/listVoucher";
    }

    @GetMapping("/updateVoucher")
    public String updateVoucher(@RequestParam("id")Integer id){
        Voucher voucher = voucherService.getById(id);
        voucherService.update(voucher);
        return "/admin_view/create-voucher";
    }

    @GetMapping("/deleteVoucher")
    public String deleteVoucher(@RequestParam("id") Integer id){
        voucherService.deleteById(id);
        return "redirect:/admin/listVoucher";
    }
}
