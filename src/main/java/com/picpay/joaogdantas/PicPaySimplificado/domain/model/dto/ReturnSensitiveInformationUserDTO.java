package com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto;

import com.picpay.joaogdantas.PicPaySimplificado.domain.model.PicPayUserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ReturnSensitiveInformationUserDTO(
        Integer id,
        String name,
        String cpf,
        String email,
        String password,
        PicPayUserType userType,
        Double balance
) {
}
