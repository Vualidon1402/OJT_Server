package com.ojt_server.modules.product_detail;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ojt_server.modules.config.ConfigModel;
import com.ojt_server.modules.product.ProductModel;
import com.ojt_server.modules.color.ColorModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "product_detail")
public class ProductDetailModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image", length = 255)
    private String image;

    @Column(name = "product_detail_name", length = 255)
    private String productDetailName;

    @Column(name = "status")
    private boolean status = true;

    @Column(name = "stock", nullable = false, columnDefinition = "INT(11)")
    private int stock;

    @Column(name = "unit_price", nullable = false)
    private double unitPrice;

    @Column(name = "discount")
    private double discount;

    @Column(name = "discount_price")
    private double discountPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "color_id")
    private ColorModel color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "config_id")
    private ConfigModel config;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private ProductModel product;

    @PrePersist
    @PreUpdate
    private void calculateDiscountPrice() {
        this.discountPrice = this.unitPrice * (100 - this.discount) / 100;
    }

    public ProductDetailModel(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProductDetailModel{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", productDetailName='" + productDetailName + '\'' +
                ", status=" + status +
                ", stock=" + stock +
                ", unitPrice=" + unitPrice +
                ", discount=" + discount +
                ", discountPrice=" + discountPrice +
                ", color=" + color +
                ", config=" + config +
                ", product=" + product +
                '}';
    }
}