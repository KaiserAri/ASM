package poly.edu.asmn1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import poly.edu.asmn1.dto.ProductDTO;
import poly.edu.asmn1.entity.Product;
import poly.edu.asmn1.mapper.ProductMapper;
import poly.edu.asmn1.repository.ProductRepository;
import poly.edu.asmn1.repository.CategoryRepository;
import poly.edu.asmn1.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired ProductRepository repo;
    @Autowired ProductMapper mapper;

    @Override
    public Page<ProductDTO> findAll(Pageable pageable) {
        return repo.findAll(pageable).map(mapper::toDTO);
    }

    @Override
    public Page<ProductDTO> findByCategoryId(Integer cid, Pageable pageable) {
        return repo.findByCategoryId(cid, pageable).map(mapper::toDTO);
    }

    @Override
    public Page<ProductDTO> findByKeywords(String keywords, Pageable pageable) {
        return repo.findByKeywords(keywords, pageable).map(mapper::toDTO);
    }

    @Override
    public ProductDTO findById(Integer id) {
        return repo.findById(id).map(mapper::toDTO).orElse(null);
    }

    // --- Giữ nguyên Logic CRUD cũ của bạn ---
    @Override
    public void create(ProductDTO dto) {
        repo.save(mapper.toEntity(dto));
    }

    @Override
    public void update(ProductDTO dto) {
        Product entity = repo.findById(dto.getId()).orElse(null);
        if (entity != null) {
            entity.setName(dto.getName());
            entity.setPrice(dto.getPrice());
            entity.setImage(dto.getImage());
            repo.save(entity);
        }
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}