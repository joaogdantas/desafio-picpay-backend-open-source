package com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto;

import com.picpay.joaogdantas.PicPaySimplificado.domain.model.PicPayUserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record CreateUserDTO(
        @NotBlank(message = "O nome não pode ser deixado em branco")
        String name,
        @NotBlank(message = "O CPF não pode ser deixado em branco")
        @Size(min = 11, max = 11, message = "O CPF precisa ter 11 números e ser escrito sem pontuação")
        String cpf,
        @Email
        @NotBlank(message = "O email não pode ser deixado em branco")
        String email,
        @NotBlank(message = "A senha não pode ser deixada em branco")
        String password,
        @NotBlank(message = "O tipo de usuário não pode ser deixado em branco")
        PicPayUserType userType
) {
}
