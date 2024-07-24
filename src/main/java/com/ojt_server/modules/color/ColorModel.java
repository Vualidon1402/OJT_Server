package com.ojt_server.modules.color;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "color")
public class ColorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "color_name", length = 255)
    private String colorName;

    @Column(name = "status")
    private boolean status = true;

    @Override
    public String toString() {
        return "id: " + id + ", colorName: " + colorName + ", status: " + status;
    }
}