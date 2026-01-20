package poly.edu.asmn1.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.edu.asmn1.service.CartService;

@Controller
public class CartController {
    @Autowired CartService cart;

    // Luôn đưa cart vào model + cập nhật session.cartCount
    @ModelAttribute("cart")
    public CartService getCart(HttpSession session) {
        session.setAttribute("cartCount", cart.getCount());
        return cart;
    }

    @RequestMapping("/cart/view")
    public String view(Model model) {
        model.addAttribute("view", "cart/view");
        return "layout/index";
    }

    @RequestMapping("/cart/add/{id}")
    public String add(@PathVariable("id") Integer id, HttpSession session) {
        cart.add(id);
        session.setAttribute("cartCount", cart.getCount());
        return "redirect:/cart/view";
    }

    @RequestMapping("/cart/remove/{id}")
    public String remove(@PathVariable("id") Integer id, HttpSession session) {
        cart.remove(id);
        session.setAttribute("cartCount", cart.getCount());
        return "redirect:/cart/view";
    }

    @PostMapping("/cart/update/{id}")
    public String update(@PathVariable("id") Integer id, @RequestParam("qty") Integer qty, HttpSession session) {
        cart.update(id, qty);
        session.setAttribute("cartCount", cart.getCount());
        return "redirect:/cart/view";
    }

    @RequestMapping("/cart/clear")
    public String clear(HttpSession session) {
        cart.clear();
        session.setAttribute("cartCount", cart.getCount());
        return "redirect:/cart/view";
    }
}