package com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto;

import com.picpay.joaogdantas.PicPaySimplificado.domain.model.PicPayUserType;

public record ReturnSensitiveInformationUserDTO(
        Long id,
        String name,
        String cpf,
        String email,
        String password,
        PicPayUserType userType
) {
}
