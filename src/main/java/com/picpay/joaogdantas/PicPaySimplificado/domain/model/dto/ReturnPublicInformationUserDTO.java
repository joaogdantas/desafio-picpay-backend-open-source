package com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto;

import com.picpay.joaogdantas.PicPaySimplificado.domain.model.PicPayUserType;

public record ReturnPublicInformationUserDTO(
        String name,
        String email,
        PicPayUserType userType
) {
}
