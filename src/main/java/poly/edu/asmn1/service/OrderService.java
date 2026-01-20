package poly.edu.asmn1.service;

import poly.edu.asmn1.dto.OrderDTO;
import poly.edu.asmn1.dto.OrderDetailDTO;
import poly.edu.asmn1.entity.Account;

import java.util.List;

public interface OrderService {
    // Cho khách hàng
    OrderDTO create(Account account, String address, String phone, String note);
    List<OrderDTO> findByUsername(String username);
    List<OrderDetailDTO> findDetailsByOrderId(Long orderId);

    // Cho Admin (Bổ sung mới)
    List<OrderDTO> findAll(); // Lấy tất cả đơn hàng hệ thống
    void updateStatus(Long id, Integer status); // Cập nhật trạng thái đơn
}