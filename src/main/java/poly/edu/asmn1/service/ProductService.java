package poly.edu.asmn1.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import poly.edu.asmn1.dto.ProductDTO;
import poly.edu.asmn1.dto.ReportDTO;
import java.util.List;

public interface ProductService {
    // Các hàm phân trang mới
    Page<ProductDTO> findAll(Pageable pageable);
    Page<ProductDTO> findByCategoryId(Integer cid, Pageable pageable);
    Page<ProductDTO> findByKeywords(String keywords, Pageable pageable);

    ProductDTO findById(Integer id);

    // Code CRUD cũ cho Admin
    void create(ProductDTO dto);
    void update(ProductDTO dto);
    void delete(Integer id);
}