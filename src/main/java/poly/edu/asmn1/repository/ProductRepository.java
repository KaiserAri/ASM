package poly.edu.asmn1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.edu.asmn1.dto.ReportDTO;
import poly.edu.asmn1.entity.Product;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // 1. Tìm theo Category (Phân trang)
    Page<Product> findByCategoryId(Integer cid, Pageable pageable);

    // 2. Tìm theo từ khóa (Phân trang)
    @Query("SELECT p FROM Product p WHERE p.name LIKE ?1")
    Page<Product> findByKeywords(String keywords, Pageable pageable);

    // --- Giữ nguyên các code cũ của bạn ---
    List<Product> findByAvailableTrue();

    @Query("SELECT new poly.edu.asmn1.dto.ReportDTO(p.category.name, SUM(p.price), COUNT(p)) " +
            "FROM Product p GROUP BY p.category.name")
    List<ReportDTO> getInventoryByCategory();

    @Query("SELECT new poly.edu.asmn1.dto.ReportDTO(d.product.category.name, SUM(d.price * d.quantity), COUNT(d)) " +
            "FROM OrderDetail d GROUP BY d.product.category.name")
    List<ReportDTO> getRevenueByCategory();
}