package com.ojt_server.modules.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO {
    @NotBlank(message = "Khong duoc de trong")
    private String loginId;
    @NotBlank(message = "Khong duoc de trong")
    private String password;


    @Override
    public String toString() {
        return "LoginDTO{" +
                "loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
