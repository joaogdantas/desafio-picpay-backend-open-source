package com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto;

import com.picpay.joaogdantas.PicPaySimplificado.domain.model.PicPayUserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ReturnPublicInformationUserDTO(
        String name,
        String email,
        PicPayUserType userType
) {
}
