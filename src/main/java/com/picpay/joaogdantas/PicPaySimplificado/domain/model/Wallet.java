package com.picpay.joaogdantas.PicPaySimplificado.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "wallet")
@Table(name = "wallets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @OneToOne(mappedBy = "wallet")
    private User user;
}
