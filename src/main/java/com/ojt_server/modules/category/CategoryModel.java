package com.ojt_server.modules.category;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ojt_server.modules.product.ProductModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "category")
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "category_name", length = 255)
    private String categoryName;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt = new Date();

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "image", length = 255)
    private String image;

    @Column(name = "status", nullable = false)
    private boolean status = true;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<ProductModel> products;

    @Override
    public String toString() {
        return "CategoryModel{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", createdAt=" + createdAt +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", status=" + status +
                '}';
    }
}
