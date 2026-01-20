package poly.edu.asmn1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO implements Serializable {
    private Integer id;
    private String name;
    private Double price;
    private Integer quantity = 1;
    private String image; // Chứa URL Cloudinary

    // Tính tổng tiền cho 1 dòng sản phẩm
    public Double getAmount() {
        return this.price * this.quantity;
    }
}