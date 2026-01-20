package poly.edu.asmn1.mapper;

import org.springframework.stereotype.Component;
import poly.edu.asmn1.dto.CategoryDTO;
import poly.edu.asmn1.entity.Category;

@Component
public class CategoryMapper {
    public CategoryDTO toDTO(Category entity) {
        if (entity == null) return null;
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public Category toEntity(CategoryDTO dto) {
        if (dto == null) return null;
        Category entity = new Category();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}