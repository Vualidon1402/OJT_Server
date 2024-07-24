package com.ojt_server.modules.config.dto.request;

import com.ojt_server.modules.product_detail.ProductDetailModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConfigModelDTO {
    private Long id;
    private String configName;
    private Long productDetailId;

    @Override
    public String toString() {
        return "ConfigModelDTO{" +
                "id=" + id +
                ", configName='" + configName + '\'' +
                ", productDetailId=" + productDetailId +
                '}';
    }
}
