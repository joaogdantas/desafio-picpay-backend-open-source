package com.picpay.joaogdantas.PicPaySimplificado.domain.model;

import com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto.CreateUserDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

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
    private Wallet wallet;

    public User(CreateUserDTO createUserDTO){
        this.name = createUserDTO.name();
        this.cpf = createUserDTO.cpf();
        this.email = createUserDTO.email();
        this.password = createUserDTO.password();
        this.userType = createUserDTO.userType();
    }
}
