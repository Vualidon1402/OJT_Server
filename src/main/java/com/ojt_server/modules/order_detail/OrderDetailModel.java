package com.ojt_server.modules.order_detail;

import com.ojt_server.modules.product_detail.ProductDetailModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "order_detail")
public class OrderDetailModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_quantity", columnDefinition = "INT(11)", nullable = false)
    private int orderQuantity;

    @Column(name = "order_detail_name", length = 255)
    private String orderDetailName;

    @Column(name = "unit_price", nullable = false)
    private double unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_detail_id")
    private ProductDetailModel productDetail;

    @Column(name = "orders_id")
    private Long ordersId;
}
