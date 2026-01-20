package poly.edu.asmn1.dto;

import lombok.Data;
import java.util.Date;

@Data
public class OrderDTO {
    private Long id;
    private Date createDate;
    private String address;
    private Double totalAmount;
    private Integer status;
}