package com.ojt_server.modules.product.dto.request;

import com.ojt_server.modules.product_detail.dto.request.ProductDetailDTO;
import com.ojt_server.validator.UniqueField;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductModelDTO {
    private Long id;
    @NotBlank(message = "Product name is required")
    @UniqueField(fieldName = "productName", message = "Product name already exists")
    private String productName;
    @NotBlank(message = "Description is required")
    private String description;
    private String image;
    private String sku;
    private boolean status;
    private Date updatedAt;
    private Long categoryId;
    private Long brandId;
    private List<String> images;
    private List<ProductDetailDTO> productDetails;

    @Override
    public String toString() {
        return "name: " + productName + ", description: " + description + ", image: " + image + ", sku: " + sku + ", categoryId: " + categoryId + ", brandId: " + brandId;
    }

}
