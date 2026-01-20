package poly.edu.asmn1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.edu.asmn1.dto.ReportDTO;
import poly.edu.asmn1.service.ReportService;

import java.util.List;

@Controller
@RequestMapping("/admin/report")
public class AdminReportController {

    @Autowired
    private ReportService reportService;

    @RequestMapping("/index")
    public String index(Model model) {

        // ===== 1. DỮ LIỆU KHO =====
        List<ReportDTO> inventoryItems = reportService.getInventoryByCategory();
        model.addAttribute("inventoryItems", inventoryItems);

        // ===== 2. DỮ LIỆU DOANH THU =====
        List<ReportDTO> revenueItems = reportService.getRevenueByCategory();
        model.addAttribute("revenueItems", revenueItems);

        // ===== 3. TỔNG SẢN PHẨM TRONG KHO =====
        long totalInventory = inventoryItems == null ? 0 :
                inventoryItems.stream()
                        .mapToLong(r -> r.getCount() == null ? 0 : r.getCount())
                        .sum();
        model.addAttribute("totalInventory", totalInventory);

        // ===== 4. TỔNG DOANH THU =====
        double totalRevenue = revenueItems == null ? 0 :
                revenueItems.stream()
                        .mapToDouble(r -> r.getSum() == null ? 0 : r.getSum())
                        .sum();
        model.addAttribute("totalRevenue", totalRevenue);

        // ===== 5. VIEW CON =====
        model.addAttribute("view", "admin/report");
        return "layout/index";
    }
}
