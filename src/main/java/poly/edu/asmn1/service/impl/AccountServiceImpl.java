package poly.edu.asmn1.service.impl;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.edu.asmn1.dto.AccountDTO;
import poly.edu.asmn1.entity.Account;
import poly.edu.asmn1.mapper.AccountMapper;
import poly.edu.asmn1.repository.AccountRepository;
import poly.edu.asmn1.service.AccountService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository repo;

    @Autowired
    AccountMapper mapper;

    // ================= LOGIN =================
    @Override
    public Account login(String username, String password) {
        Account account = repo.findById(username).orElse(null);
        if (account == null) {
            return null;
        }

        // Check if password matches
        boolean passwordMatches = false;

        // Try BCrypt verification first - BCrypt will safely reject non-BCrypt strings
        try {
            passwordMatches = BCrypt.checkpw(password, account.getPassword());
        } catch (IllegalArgumentException e) {
            // Not a BCrypt hash, try plaintext comparison for soft migration
            passwordMatches = account.getPassword().equals(password);
            if (passwordMatches) {
                // Hash the password and update it
                try {
                    account.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
                    repo.save(account);
                } catch (Exception ex) {
                    // Log error but don't fail login if migration fails
                    // Password will be migrated on next successful login
                }
            }
        }

        return passwordMatches ? account : null;
    }

    // ================= REGISTER =================
    @Override
    public void create(AccountDTO dto) {
        // ❗ Không cho trùng username
        if (repo.existsById(dto.getUsername())) {
            throw new RuntimeException("Username đã tồn tại!");
        }

        Account entity = mapper.toEntity(dto);
        // Hash the password before saving
        entity.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
        entity.setActivated(true);
        entity.setRole(false); // Non-admin by default

        repo.save(entity);
    }

    // ================= ADMIN =================
    @Override
    public List<AccountDTO> findAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDTO findById(String username) {
        return repo.findById(username)
                .map(mapper::toDTO)
                .orElse(null);
    }

    @Override
    public void update(AccountDTO dto) {
        Account entity = repo.findById(dto.getUsername()).orElse(null);
        if (entity != null) {
            entity.setFullname(dto.getFullname());
            entity.setEmail(dto.getEmail());
            entity.setPhoto(dto.getPhoto());
            repo.save(entity);
        }
    }

    @Override
    public void delete(String username) {
        repo.deleteById(username);
    }
}