package poly.edu.asmn1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.edu.asmn1.dto.ProductDTO;
import poly.edu.asmn1.service.ProductService;
import poly.edu.asmn1.service.CategoryService;

import java.util.Optional;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam("p") Optional<Integer> p,
                        @RequestParam("cid") Optional<Integer> cid,
                        @RequestParam("keywords") Optional<String> keywords) {

        Pageable pageable = PageRequest.of(p.orElse(0), 8);
        Page<ProductDTO> page;

        if (keywords.isPresent() && !keywords.get().isBlank()) {
            page = productService.findByKeywords("%" + keywords.get() + "%", pageable);
            model.addAttribute("keywords", keywords.get());
        } else if (cid.isPresent()) {
            page = productService.findByCategoryId(cid.get(), pageable);
            model.addAttribute("cid", cid.get());
        } else {
            page = productService.findAll(pageable);
        }

        model.addAttribute("page", page);
        model.addAttribute("items", page.getContent()); // ✅ CHO HTML
        model.addAttribute("item", new ProductDTO());
        model.addAttribute("cates", categoryService.findAll());

        model.addAttribute("view", "admin/product");
        return "layout/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id,
                       @RequestParam("p") Optional<Integer> p,
                       Model model) {

        Pageable pageable = PageRequest.of(p.orElse(0), 8);

        Page<ProductDTO> page = productService.findAll(pageable);

        model.addAttribute("item", productService.findById(id));
        model.addAttribute("page", page);
        model.addAttribute("items", page.getContent());
        model.addAttribute("cates", categoryService.findAll());

        model.addAttribute("view", "admin/product");
        return "layout/index";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("item") ProductDTO item) {
        productService.create(item);
        return "redirect:/admin/product/index";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("item") ProductDTO item) {
        productService.update(item);
        return "redirect:/admin/product/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productService.delete(id);
        return "redirect:/admin/product/index";
    }
}