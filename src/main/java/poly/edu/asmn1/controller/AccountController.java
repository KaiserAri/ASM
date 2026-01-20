package poly.edu.asmn1.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import poly.edu.asmn1.dto.AccountDTO;
import poly.edu.asmn1.entity.Account;
import poly.edu.asmn1.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    // ================= LOGIN =================
    @GetMapping("/login")
    public String loginForm() {
        return "account/login";
    }

    @PostMapping("/login")
    public String login(Model model,
                        @RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {

        Account user = accountService.login(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/";
        }
        model.addAttribute("message", "Sai tài khoản hoặc mật khẩu!");
        return "account/login";
    }

    // ================= LOGOUT =================
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // ================= REGISTER =================
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("account", new AccountDTO());
        model.addAttribute("view", "account/register");
        return "layout/index";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("account") AccountDTO dto,
                           BindingResult result,
                           Model model) {

        if (result.hasErrors()) {
            model.addAttribute("view", "account/register");
            return "layout/index";
        }

        accountService.create(dto); // ✅ ĐÚNG NGHIỆP VỤ
        return "redirect:/account/login";
    }
}