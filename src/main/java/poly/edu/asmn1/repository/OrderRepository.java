package poly.edu.asmn1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.edu.asmn1.entity.Order;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // Tìm các đơn hàng của một khách hàng cụ thể dựa trên Username
    @Query("SELECT o FROM Order o WHERE o.account.username = ?1")
    List<Order> findByUsername(String username);
}