package poly.edu.asmn1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.edu.asmn1.dto.ProductDTO;
import poly.edu.asmn1.service.ProductService;
import poly.edu.asmn1.service.CategoryService;

import java.util.Optional;

@Controller
public class ProductController {
    @Autowired ProductService productService;
    @Autowired CategoryService categoryService;

    @RequestMapping("/")
    public String home(Model model,
                       @RequestParam("cid") Optional<Integer> cid,
                       @RequestParam("p") Optional<Integer> p) {

        Pageable pageable = PageRequest.of(p.orElse(0), 6);
        Page<ProductDTO> page;

        model.addAttribute("cates", categoryService.findAll());

        if (cid.isPresent()) {
            page = productService.findByCategoryId(cid.get(), pageable);
            model.addAttribute("cid", cid.get());
        } else {
            page = productService.findAll(pageable);
        }

        model.addAttribute("page", page);
        model.addAttribute("view", "product/list");
        return "layout/index";
    }

    @RequestMapping("/product/search")
    public String search(Model model,
                         @RequestParam("keywords") String keywords,
                         @RequestParam("p") Optional<Integer> p) {

        Pageable pageable = PageRequest.of(p.orElse(0), 6);
        Page<ProductDTO> page = productService.findByKeywords("%" + keywords + "%", pageable);

        model.addAttribute("page", page);
        model.addAttribute("keywords", keywords);
        model.addAttribute("view", "product/list");
        return "layout/index";
    }

    // ✅ Thêm route chi tiết sản phẩm
    @RequestMapping("/product/detail/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        ProductDTO item = productService.findById(id);
        model.addAttribute("item", item);
        model.addAttribute("cates", categoryService.findAll());
        model.addAttribute("view", "product/detail");
        return "layout/index";
    }
}