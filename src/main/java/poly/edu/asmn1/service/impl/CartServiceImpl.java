package poly.edu.asmn1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import poly.edu.asmn1.dto.CartItemDTO;
import poly.edu.asmn1.entity.Product;
import poly.edu.asmn1.mapper.CartItemMapper;
import poly.edu.asmn1.repository.ProductRepository;
import poly.edu.asmn1.service.CartService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@SessionScope // Quan trọng nhất: Giữ giỏ hàng tồn tại trong 1 phiên làm việc (Slide 5)
public class CartServiceImpl implements CartService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CartItemMapper cartMapper;

    // Map dùng để chứa giỏ hàng (Key là ID sản phẩm)
    private Map<Integer, CartItemDTO> map = new HashMap<>();

    @Override
    public void add(Integer id) {
        CartItemDTO item = map.get(id);
        if (item == null) {
            // Nếu chưa có trong giỏ, tìm trong DB và map sang DTO
            Product p = productRepo.findById(id).orElse(null);
            if (p != null) {
                item = cartMapper.toCartItem(p);
                map.put(id, item);
            }
        } else {
            // Nếu có rồi thì tăng số lượng lên 1
            item.setQuantity(item.getQuantity() + 1);
        }
    }

    @Override
    public void remove(Integer id) {
        map.remove(id);
    }

    @Override
    public void update(Integer id, int qty) {
        CartItemDTO item = map.get(id);
        if (item != null) {
            item.setQuantity(qty);
            if (item.getQuantity() <= 0) {
                map.remove(id);
            }
        }
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Collection<CartItemDTO> getItems() {
        return map.values();
    }

    @Override
    public int getCount() {
        return map.values().stream().mapToInt(item -> item.getQuantity()).sum();
    }

    @Override
    public double getAmount() {
        return map.values().stream().mapToDouble(item -> item.getAmount()).sum();
    }
}