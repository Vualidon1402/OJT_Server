package com.ojt_server.modules.config;

import com.ojt_server.modules.product_detail.ProductDetailModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "config")
public class ConfigModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "config_name", length = 255)
    private String configName;

    @Column(name = "status")
    private boolean status = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_detail_id")
    private ProductDetailModel productDetail;
}
