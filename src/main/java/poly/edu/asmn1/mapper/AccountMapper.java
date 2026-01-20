package poly.edu.asmn1.mapper;

import org.springframework.stereotype.Component;
import poly.edu.asmn1.dto.AccountDTO;
import poly.edu.asmn1.entity.Account;

@Component
public class AccountMapper {
    public AccountDTO toDTO(Account entity) {
        if (entity == null) return null;
        AccountDTO dto = new AccountDTO();
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setFullname(entity.getFullname());
        dto.setEmail(entity.getEmail());
        dto.setPhoto(entity.getPhoto());
        return dto;
    }

    public Account toEntity(AccountDTO dto) {
        if (dto == null) return null;
        Account entity = new Account();
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setFullname(dto.getFullname());
        entity.setEmail(dto.getEmail());
        entity.setPhoto(dto.getPhoto());
        return entity;
    }
}