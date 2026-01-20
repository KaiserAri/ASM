package poly.edu.asmn1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO implements Serializable {
    private Long id;
    private Double price;
    private Integer quantity;

    // Thông tin bổ sung từ bảng Product để hiển thị lên giao diện
    private Integer productId;
    private String productName;
    private String productImage; // URL Cloudinary

    // Thuận tiện cho việc tính tổng tiền của dòng này trên giao diện
    public Double getSubtotal() {
        return this.price * this.quantity;
    }
}