package taqwa.uz.taqwa.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    @NotBlank(message = "Username bo'sh bo'lmasligi kerak")
    private String username;

    @Email(message = "Email formatida xatolik bor")
    @NotBlank(message = "Email bo'sh bo'lmasligi kerak")
    private String email;

    @Size(min = 6, message = "Parol kamida 6 ta belgidan iborat bo'lsin")
    private String password;

    private String confirmPassword;
}