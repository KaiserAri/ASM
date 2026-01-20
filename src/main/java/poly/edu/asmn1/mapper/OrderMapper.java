package poly.edu.asmn1.mapper;

import org.springframework.stereotype.Component;
import poly.edu.asmn1.dto.OrderDTO;
import poly.edu.asmn1.entity.Order;

@Component
public class OrderMapper {
    public OrderDTO toDTO(Order entity) {
        if (entity == null) return null;
        OrderDTO dto = new OrderDTO();
        dto.setId(entity.getId());
        dto.setCreateDate(entity.getCreateDate());
        dto.setAddress(entity.getAddress());
        dto.setStatus(entity.getStatus());

        if (entity.getOrderDetails() != null) {
            double total = entity.getOrderDetails().stream()
                    .mapToDouble(d -> d.getPrice() * d.getQuantity()).sum();
            dto.setTotalAmount(total);
        }
        return dto;
    }

    public Order toEntity(OrderDTO dto) {
        if (dto == null) return null;
        Order entity = new Order();
        entity.setId(dto.getId());
        entity.setAddress(dto.getAddress());
        entity.setStatus(dto.getStatus());
        entity.setCreateDate(dto.getCreateDate());
        return entity;
    }
}