package com.ojt_server.modules.product_detail;

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

    @Column(name = "status", nullable = false)
    private boolean status = true;

    @Column(name = "stock", nullable = false, columnDefinition = "INT(11)")
    private int stock;

    @Column(name = "unit_price", nullable = false)
    private double unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private ColorModel color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductModel product;

    @Override
    public String toString() {
        return "ProductDetailModel{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", productDetailName='" + productDetailName + '\'' +
                ", status=" + status +
                ", stock=" + stock +
                ", unitPrice=" + unitPrice +
                ", color=" + color +
                ", product=" + product +
                '}';
    }
}
