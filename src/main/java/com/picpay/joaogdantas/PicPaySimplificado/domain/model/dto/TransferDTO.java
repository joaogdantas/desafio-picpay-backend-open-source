package com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto;

public record TransferDTO(
    String payerName,
    String payeeName,
    Double value

) {
}
