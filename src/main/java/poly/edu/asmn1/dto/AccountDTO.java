package poly.edu.asmn1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountDTO {
    @NotBlank(message = "Username không được để trống")
    @Size(min = 4, max = 30, message = "Username từ 4-30 ký tự")
    private String username;

    @NotBlank(message = "Password không được để trống")
    @Size(min = 6, message = "Password tối thiểu 6 ký tự")
    private String password;

    @NotBlank(message = "Họ tên không được để trống")
    private String fullname;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    private String photo;
}