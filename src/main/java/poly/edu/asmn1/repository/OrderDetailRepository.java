package poly.edu.asmn1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.asmn1.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}