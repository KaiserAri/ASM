package poly.edu.asmn1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.edu.asmn1.dto.AccountDTO;
import poly.edu.asmn1.service.AccountService;

@Controller
@RequestMapping("/admin/account")
public class AdminAccountController {
    @Autowired AccountService accountService;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("item", new AccountDTO()); // Form rỗng
        model.addAttribute("items", accountService.findAll());
        return "admin/account";
    }

    @RequestMapping("/edit/{username}")
    public String edit(Model model, @PathVariable("username") String username) {
        model.addAttribute("item", accountService.findById(username));
        model.addAttribute("items", accountService.findAll());
        return "admin/account";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("item") AccountDTO item) {
        accountService.update(item);
        return "redirect:/admin/account/index";
    }
}