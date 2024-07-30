package com.ojt_server.modules.address.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressUpdateRequest {
    private Long userId;
    private String receiveName;
    private String streetAddress;
    private String district;
    private String province;
    private String ward;
    private String phone;
    private boolean priority;

}
