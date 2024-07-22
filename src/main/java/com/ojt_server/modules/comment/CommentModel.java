package com.ojt_server.modules.comment;

import com.ojt_server.modules.product.ProductModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "comment")
public class CommentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment", length = 255)
    private String comment;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "status")
    private boolean status = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductModel product;

    @Column(name = "user_id")
    private Long userId;
}
