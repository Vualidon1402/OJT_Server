package com.ojt_server.modules.user.model;


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
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "avatar", length = 255)
    private String avatar = "http://localhost:1234/notimg.jpg";

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "email", length = 255,unique = true)
    private String email;

    @Column(name = "full_name", length = 255)
    private String fullName;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted ;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "phone", length = 255,unique = true)
    private String phone;

    @Column(name = "point", nullable = false)
    private double point;

    @Column(name = "status")
    private boolean status = true;

    @Temporal(TemporalType.DATE)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "username", length = 255,unique = true)
    private String username;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable( // bảng trung gian
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )

    private Set<Role> roles;

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", createdAt=" + createdAt +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", isDeleted=" + isDeleted +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", point=" + point +
                ", status=" + status +
                ", updatedAt=" + updatedAt +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                '}';
    }
}
