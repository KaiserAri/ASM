package poly.edu.asmn1.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.edu.asmn1.entity.Account;
import poly.edu.asmn1.service.CartService;
import poly.edu.asmn1.service.OrderService;

import java.util.List;

@Controller
public class OrderController {
    @Autowired OrderService orderService;
    @Autowired CartService cart;

    // Luôn đưa cart vào model + cập nhật session.cartCount
    @ModelAttribute("cart")
    public CartService getCart(HttpSession session) {
        session.setAttribute("cartCount", cart.getCount());
        return cart;
    }

    @RequestMapping("/order/checkout")
    public String checkout(Model model) {
        model.addAttribute("view", "order/checkout");
        return "layout/index";
    }

    @PostMapping("/order/confirm")
    public String confirm(HttpSession session,
                          @RequestParam("address") String address,
                          @RequestParam("phone") String phone,
                          @RequestParam("note") String note) {
        Account user = (Account) session.getAttribute("user");
        orderService.create(user, address, phone, note);
        cart.clear();
        session.setAttribute("cartCount", cart.getCount());
        return "redirect:/order/list";
    }

    @RequestMapping("/order/list")
    public String list(Model model, HttpSession session) {
        Account user = (Account) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("orders", orderService.findByUsername(user.getUsername()));
        }
        model.addAttribute("view", "order/list");
        return "layout/index";
    }

    @RequestMapping("/order/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        List<?> details = orderService.findDetailsByOrderId(id);
        model.addAttribute("details", details);
        model.addAttribute("orderId", id);
        model.addAttribute("view", "order/detail");
        return "layout/index";
    }
}