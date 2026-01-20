package poly.edu.asmn1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.edu.asmn1.service.OrderService;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {
    @Autowired OrderService orderService;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("items", orderService.findAll()); // Lấy tất cả đơn hàng của hệ thống
        return "admin/order";
    }

    @PostMapping("/update-status")
    public String updateStatus(@RequestParam("id") Long id, @RequestParam("status") Integer status) {
        orderService.updateStatus(id, status);
        return "redirect:/admin/order/index";
    }
}