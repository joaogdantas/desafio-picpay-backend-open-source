package com.picpay.joaogdantas.PicPaySimplificado.domain.exceptions;

public class TransferIsInvalidException extends RuntimeException {

    public TransferIsInvalidException() {
        super("A transferência não é valida");
    }

    public TransferIsInvalidException(String message) { super(message);}
}
