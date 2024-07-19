package com.ojt_server.modules.product;

import com.ojt_server.modules.category.CategoryModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "product")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "image", length = 255)
    private String image;

    @Column(name = "product_name", length = 255)
    private String productName;

    @Column(name = "sku", length = 255)
    private String sku;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_at")
    private Date updatedAt;

    @Column(name = "brand_id")
    private Long brandId;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryModel category;

    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", productName='" + productName + '\'' +
                ", sku='" + sku + '\'' +
                ", status=" + status +
                ", updatedAt=" + updatedAt +
                ", brandId=" + brandId +
                ", category=" + category +
                '}';
    }
}
