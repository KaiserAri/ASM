package poly.edu.asmn1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.edu.asmn1.dto.CategoryDTO;
import poly.edu.asmn1.entity.Category;
import poly.edu.asmn1.mapper.CategoryMapper;
import poly.edu.asmn1.repository.CategoryRepository;
import poly.edu.asmn1.service.CategoryService;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired CategoryRepository repo;
    @Autowired CategoryMapper mapper;

    @Override
    public List<CategoryDTO> findAll() {
        return repo.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(Integer id) {
        return repo.findById(id).map(mapper::toDTO).orElse(null);
    }

    @Override
    public void create(CategoryDTO dto) {
        Category entity = mapper.toEntity(dto);
        repo.save(entity);
    }

    @Override
    public void update(CategoryDTO dto) {
        Category entity = mapper.toEntity(dto);
        repo.save(entity);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}