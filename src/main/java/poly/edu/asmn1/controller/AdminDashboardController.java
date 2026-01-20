package poly.edu.asmn1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import poly.edu.asmn1.dto.*;
import poly.edu.asmn1.service.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    @Autowired ProductService productService;
    @Autowired CategoryService categoryService;
    @Autowired OrderService orderService;
    @Autowired ReportService reportService;

    @RequestMapping({"", "/index"})
    public String index(Model model,
                        @RequestParam("p") Optional<Integer> p,
                        @RequestParam("cid") Optional<Integer> cid,
                        @RequestParam("keywords") Optional<String> keywords) {

        // ===== PRODUCT =====
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
        model.addAttribute("items", page.getContent());
        model.addAttribute("item", new ProductDTO());
        model.addAttribute("cates", categoryService.findAll());

        // ===== CATEGORY =====
        model.addAttribute("itemCategory", new CategoryDTO());
        model.addAttribute("categories", categoryService.findAll());

        // ===== ORDER =====
        model.addAttribute("orders", orderService.findAll());

        // ===== REPORT =====
        List<ReportDTO> inventoryItems = reportService.getInventoryByCategory();
        List<ReportDTO> revenueItems = reportService.getRevenueByCategory();
        model.addAttribute("inventoryItems", inventoryItems);
        model.addAttribute("revenueItems", revenueItems);
        model.addAttribute("totalInventory", inventoryItems.stream().mapToLong(r -> r.getCount() == null ? 0 : r.getCount()).sum());
        model.addAttribute("totalRevenue", revenueItems.stream().mapToDouble(r -> r.getSum() == null ? 0 : r.getSum()).sum());

        return "admin/index";
    }
}