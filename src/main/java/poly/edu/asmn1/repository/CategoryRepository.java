package poly.edu.asmn1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.asmn1.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}