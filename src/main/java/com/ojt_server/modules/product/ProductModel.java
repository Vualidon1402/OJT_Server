package com.ojt_server.modules.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ojt_server.modules.brand.BrandModel;
import com.ojt_server.modules.category.CategoryModel;
import com.ojt_server.modules.comment.CommentModel;
import com.ojt_server.modules.image.ImageModel;
import com.ojt_server.modules.product_detail.ProductDetailModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    private Date createdAt = new Date();

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "image", length = 255)
    private String image;

    @Column(name = "product_name", length = 255)
    private String productName;

    @Column(name = "sku", length = 255)
    private String sku ;

    @Column(name = "status", nullable = false)
    private boolean status = true;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_at")
    private Date updatedAt = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private BrandModel brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "products"})
    private CategoryModel category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ImageModel> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentModel> comments;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ProductDetailModel> productDetails;

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
                ", brand=" + brand +
                ", category=" + category +
                ", images=" + images +
                ", comments=" + comments +
                ", productDetails=" + productDetails +
                '}';
    }
}
