package poly.edu.asmn1.mapper;

import org.springframework.stereotype.Component;
import poly.edu.asmn1.dto.ProductDTO;
import poly.edu.asmn1.entity.Product;
import poly.edu.asmn1.entity.Category;

@Component
public class ProductMapper {
    public ProductDTO toDTO(Product entity) {
        if (entity == null) return null;
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setImage(entity.getImage());
        if (entity.getCategory() != null) {
            dto.setCategoryName(entity.getCategory().getName());
        }
        return dto;
    }

    public Product toEntity(ProductDTO dto) {
        if (dto == null) return null;
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setImage(dto.getImage());
        entity.setAvailable(true);
        // Category sẽ được Service set sau khi tìm kiếm từ DB
        return entity;
    }
}