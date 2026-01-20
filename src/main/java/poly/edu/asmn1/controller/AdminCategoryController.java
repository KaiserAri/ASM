    package poly.edu.asmn1.controller;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;
    import poly.edu.asmn1.dto.CategoryDTO;
    import poly.edu.asmn1.service.CategoryService;

    @Controller
    @RequestMapping("/admin/category")
    public class AdminCategoryController {
        @Autowired CategoryService categoryService;

        @RequestMapping("/index")
        public String index(Model model) {
            model.addAttribute("item", new CategoryDTO());
            model.addAttribute("items", categoryService.findAll());
            return "admin/category";
        }

        @PostMapping("/create")
        public String create(CategoryDTO item) {
            categoryService.create(item); // Service sẽ gọi Mapper thủ công để lưu
            return "redirect:/admin/category/index";
        }

        @RequestMapping("/delete/{id}")
        public String delete(@PathVariable("id") Integer id) {
            categoryService.delete(id);
            return "redirect:/admin/category/index";
        }
    }