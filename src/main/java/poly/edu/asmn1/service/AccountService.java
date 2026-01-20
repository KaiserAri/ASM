package poly.edu.asmn1.service;

import poly.edu.asmn1.dto.AccountDTO;
import poly.edu.asmn1.entity.Account;
import java.util.List;

public interface AccountService {

    // ========= CLIENT =========
    Account login(String username, String password);

    // ========= REGISTER =========
    void create(AccountDTO dto);   // ✅ THÊM MỚI

    // ========= ADMIN =========
    List<AccountDTO> findAll();
    AccountDTO findById(String username);
    void update(AccountDTO dto);
    void delete(String username);
}
