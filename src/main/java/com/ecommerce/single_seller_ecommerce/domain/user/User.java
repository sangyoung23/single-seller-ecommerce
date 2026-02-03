package com.ecommerce.single_seller_ecommerce.domain.user;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본 키

    @Column(nullable = false, unique = true, length = 100)
    private String email; // 이메일

    @Column(nullable = false, length = 255)
    private String password; // 패스워드

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserRole role; // 사용자 권한

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserStatus status; // 사용자 상태
}
