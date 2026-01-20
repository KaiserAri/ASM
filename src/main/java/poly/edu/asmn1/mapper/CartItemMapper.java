package poly.edu.asmn1.mapper;

import org.springframework.stereotype.Component;
import poly.edu.asmn1.dto.CartItemDTO;
import poly.edu.asmn1.entity.Product;

@Component
public class CartItemMapper {
    public CartItemDTO toCartItem(Product product) {
        if (product == null) return null;
        CartItemDTO item = new CartItemDTO();
        item.setId(product.getId());
        item.setName(product.getName());
        item.setPrice(product.getPrice());
        item.setImage(product.getImage());
        item.setQuantity(1); // Mặc định khi thêm mới là 1
        return item;
    }
}