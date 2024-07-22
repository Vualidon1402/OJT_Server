package com.ojt_server.modules.brand;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "brand")
public class BrandModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_name", length = 255)
    private String brandName;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "image", length = 255)
    private String image;

    @Column(name = "status", nullable = false)
    private boolean status = true;
}
