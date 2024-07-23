package com.ojt_server.modules.address;

import com.ojt_server.modules.user.model.UserModel;
import jakarta.persistence.*;
import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( length = 255)
    private String district;
    @Column( length = 255)
    private String phone;
    @Column( length = 255)
    private boolean priority;
    @Column( length = 255)
    private String province;
    @Column(name = "receive_name", length = 255)
    private String receiveName;
    @Column(name = "street_address", length = 255)
    private String streetAddress;
    @Column( length = 255)
    private String ward;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userId;
}

