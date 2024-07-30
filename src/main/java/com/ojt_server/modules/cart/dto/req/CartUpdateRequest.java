package com.ojt_server.modules.cart.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartUpdateRequest {
    private Long id;
    private Long userId;
    private Long productDetailId;
    private int quantity;





}
