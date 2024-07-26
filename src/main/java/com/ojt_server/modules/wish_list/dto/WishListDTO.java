package com.ojt_server.modules.wish_list.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WishListDTO {
    private Long productId;
    private Long userId;

    @Override
    public String toString() {
        return "WishListDTO{" +
                "productId=" + productId +
                ", userId=" + userId +
                '}';
    }


}
