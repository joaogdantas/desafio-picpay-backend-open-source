package com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record TransferDTO(
        @NotBlank(message = "O nome de quem pagar não pode ser deixado em branco")
    String payerName,
    @NotBlank(message = "O nome de quem vai receber não pode ser deixado em branco")
    String payeeName,
    @Positive(message = "O valor tem que ser positivo")
        @NotBlank(message = "O valor não pode ser deixado em branco")
    Double value
) {
}
