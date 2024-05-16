package com.picpay.joaogdantas.PicPaySimplificado.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf", unique = true, nullable = false, length = 11)
    private String cpf;

    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "user_type", nullable = false)
    private PicPayUserType userType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="wallet_id", referencedColumnName="id")
    private Wallet walletId;
}
