package com.ojt_server.modules.order_detail.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailDTO {
    private Long id;
    private int orderQuantity;
    private String orderDetailName;
    private double unitPrice;
    private Long productDetailId;
    private Long ordersId;

    @Override
    public String toString() {
        return "id: " + id + ", orderQuantity: " + orderQuantity + ", orderDetailName: " + orderDetailName + ", unitPrice: " + unitPrice + ", productDetailId: " + productDetailId + ", ordersId: " + ordersId;
    }

}
