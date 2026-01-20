package poly.edu.asmn1.service;

import poly.edu.asmn1.dto.CartItemDTO;
import java.util.Collection;

public interface CartService {
    void add(Integer id);           // Thêm sản phẩm vào giỏ
    void remove(Integer id);        // Xóa sản phẩm khỏi giỏ
    void update(Integer id, int qty); // Cập nhật số lượng
    void clear();                   // Xóa sạch giỏ hàng
    Collection<CartItemDTO> getItems(); // Lấy danh sách sản phẩm trong giỏ
    int getCount();                 // Tổng số lượng các mặt hàng
    double getAmount();             // Tổng tiền của cả giỏ hàng
}