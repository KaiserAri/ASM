package poly.edu.asmn1.dto;

import lombok.*;

@Data // Tự động tạo Getter/Setter (Khắc phục lỗi NotReadableProperty)
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Integer id;
    private String name;
    private Double price;
    private String image;
    private Boolean available;

    // PHẢI CHÍNH XÁC TÊN NÀY (Khớp với th:field trong HTML)
    private Integer categoryId;

    private String categoryName;
}