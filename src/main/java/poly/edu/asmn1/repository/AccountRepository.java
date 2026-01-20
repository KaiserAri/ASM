package poly.edu.asmn1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.asmn1.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
    // Tự động tìm tài khoản theo username (đã có sẵn trong JpaRepository)
}