package poly.edu.asmn1.service;

import poly.edu.asmn1.dto.OrderDetailDTO;
import java.util.List;

public interface OrderDetailService {
    // Lấy danh sách chi tiết các món hàng theo ID đơn hàng
    List<OrderDetailDTO> findByOrderId(Long orderId);

    // Tìm một chi tiết cụ thể (nếu cần)
    OrderDetailDTO findById(Long id);
}