package poly.edu.asmn1.service;

import poly.edu.asmn1.dto.CategoryDTO;
import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();
    CategoryDTO findById(Integer id);
    void create(CategoryDTO dto);
    void update(CategoryDTO dto);
    void delete(Integer id);
}