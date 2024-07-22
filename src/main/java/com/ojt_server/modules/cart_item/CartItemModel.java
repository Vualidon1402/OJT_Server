package com.ojt_server.modules.cartitem;

import com.ojt_server.modules.product_detail.ProductDetailModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "cart_item")
public class CartItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", columnDefinition = "INT(11)")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_detail_id")
    private ProductDetailModel productDetail;

    @Column(name = "user_id")
    private Long userId;
}
