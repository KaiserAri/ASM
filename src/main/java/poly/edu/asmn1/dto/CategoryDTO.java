package poly.edu.asmn1.dto;

import lombok.Data;

@Data
public class CategoryDTO {
    private Integer id;
    private String name;
    private Long productCount; // Có thể dùng để hiển thị số lượng sản phẩm trong mỗi loại
}