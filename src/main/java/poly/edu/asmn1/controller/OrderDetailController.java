package poly.edu.asmn1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.edu.asmn1.dto.OrderDetailDTO;
import poly.edu.asmn1.service.OrderDetailService;

import java.util.List;

@Controller
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    // Slide 2: Sử dụng @PathVariable để bắt mã đơn hàng từ URL
    @RequestMapping("/order/detail/{orderId}")
    public String viewDetail(Model model, @PathVariable("orderId") Long orderId) {
        // Lấy danh sách OrderDetailDTO từ Service
        List<OrderDetailDTO> details = orderDetailService.findByOrderId(orderId);

        model.addAttribute("details", details);
        model.addAttribute("orderId", orderId);

        // Tính tổng tiền đơn hàng để hiển thị ở cuối bảng (Slide 3 - Thymeleaf)
        double total = details.stream().mapToDouble(d -> d.getSubtotal()).sum();
        model.addAttribute("totalAmount", total);

        return "order/detail"; // Trả về trang templates/order/detail.html
    }
}