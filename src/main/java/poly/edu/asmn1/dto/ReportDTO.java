package poly.edu.asmn1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO implements Serializable {
    private Serializable group; // Lưu tên danh mục hoặc thời gian
    private Double sum;         // Tổng tiền
    private Long count;        // Tổng số lượng
}