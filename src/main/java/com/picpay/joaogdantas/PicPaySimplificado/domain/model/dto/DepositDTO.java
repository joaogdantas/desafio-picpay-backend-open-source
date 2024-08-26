package com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record DepositDTO(
        @NotBlank(message = "O nome não pode ser deixado em branco")
        String Name,
        @NotBlank(message = "O valor não pode ser deixado em branco")
        Double value
) {
}
