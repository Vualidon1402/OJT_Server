package com.ojt_server.modules.review;

import com.ojt_server.modules.product_detail.ProductDetailModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "review")
public class ReviewModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comments", length = 255)
    private String comments;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "rating", columnDefinition = "INT(11)")
    private int rating;

    @Column(name = "status")
    private boolean status = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_detail_id")
    private ProductDetailModel productDetail;
}
