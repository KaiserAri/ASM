package poly.edu.asmn1.mapper;

import org.springframework.stereotype.Component;
import poly.edu.asmn1.dto.OrderDetailDTO;
import poly.edu.asmn1.entity.OrderDetail;

@Component
public class OrderDetailMapper {
    public OrderDetailDTO toDTO(OrderDetail entity) {
        if (entity == null) return null;
        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setId(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setQuantity(entity.getQuantity());
        if (entity.getProduct() != null) {
            dto.setProductId(entity.getProduct().getId());
            dto.setProductName(entity.getProduct().getName());
            dto.setProductImage(entity.getProduct().getImage());
        }
        return dto;
    }

    public OrderDetail toEntity(OrderDetailDTO dto) {
        if (dto == null) return null;
        OrderDetail entity = new OrderDetail();
        entity.setId(dto.getId());
        entity.setPrice(dto.getPrice());
        entity.setQuantity(dto.getQuantity());
        return entity;
    }
}