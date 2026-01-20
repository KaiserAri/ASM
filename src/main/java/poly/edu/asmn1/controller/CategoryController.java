package poly.edu.asmn1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.edu.asmn1.service.CategoryService;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Slide 2: Chia sẻ danh sách danh mục cho các trang thông qua Model
    @RequestMapping("/category/list")
    public String list(Model model) {
        // Gọi Service -> Nhận danh sách CategoryDTO
        model.addAttribute("categories", categoryService.findAll());
        return "layout/_menu"; // Thường dùng để render vào một fragment menu (Slide 4)
    }
}