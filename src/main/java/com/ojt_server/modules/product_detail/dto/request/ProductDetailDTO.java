package com.ojt_server.modules.product_detail.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDetailDTO {
    private Long id;

    private String image;

    private String productDetailName;

    @NotBlank(message = "status is required")
    private int stock;

    @NotBlank(message = "status is required")
    private double unitPrice;

    private Long colorId;

    private Long productId;

    private Long configId;

}
