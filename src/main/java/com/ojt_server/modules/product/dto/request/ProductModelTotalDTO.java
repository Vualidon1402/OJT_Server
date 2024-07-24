package com.ojt_server.modules.product.dto.request;

import com.ojt_server.modules.image.dto.request.ImageModelDTO;
import com.ojt_server.modules.product_detail.dto.request.ProductDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductModelTotalDTO {
    private ProductModelDTO product;
    private List<ImageModelDTO> images;
    private List<ProductDetailDTO> productDetail;

}
