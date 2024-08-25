package com.picpay.joaogdantas.PicPaySimplificado.domain.exceptions;

public class StoreCannotInitiateTransfersException extends RuntimeException {
    public StoreCannotInitiateTransfersException() {
        super("Lojistas não podem realizar transferências");
    }

    public StoreCannotInitiateTransfersException(String message) { super(message);}
}
