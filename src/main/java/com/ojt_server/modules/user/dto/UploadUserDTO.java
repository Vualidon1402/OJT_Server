package com.ojt_server.modules.user.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UploadUserDTO {
    private String userName;
    private String fullName;
    private String email;
    private String phone;
    private String avatar;


}
