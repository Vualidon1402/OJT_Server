package com.ojt_server.modules.user.dto;


import com.ojt_server.validator.UniqueField;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterDTO {
    @NotBlank(message = "Khong duoc de trong")
    @Size(min = 4, max = 20, message = "Do dai tu 6 den 20 ky tu")
    @UniqueField(fieldName = "userName", message = "Tài khoản đã tồn tại")
    private String username;
    @NotBlank(message = "Khong duoc de trong")
    @Size(min = 3, max = 20, message = "Do dai tu 3 den 20 ky tu")
    private String password;
    @NotBlank(message = "Khong duoc de trong")
    @Email(message = "Email khong hop le")
    @UniqueField(fieldName = "email", message = "Email đã tồn tại")
    private String email;
    @NotBlank(message = "Khong duoc de trong")
    @Size(min = 10, max = 10, message = "Do dai 10 ky tu")
    @UniqueField(fieldName = "phone", message = "Số điện thoại đã tồn tại")
    private String phone;

}
