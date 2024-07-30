package com.ojt_server.modules.cart;


import com.ojt_server.modules.product_detail.ProductDetailModel;
import com.ojt_server.modules.user.model.UserModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", columnDefinition = "INT(11)")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "productDetailId")
    private ProductDetailModel productDetail;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserModel user;

}
