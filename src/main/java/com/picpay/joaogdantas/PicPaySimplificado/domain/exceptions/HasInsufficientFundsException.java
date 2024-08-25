package com.picpay.joaogdantas.PicPaySimplificado.domain.exceptions;

public class HasInsufficientFundsException extends RuntimeException{
    public HasInsufficientFundsException() {
        super("Você não possui saldo suficiente para realizar a operação");
    }

    public HasInsufficientFundsException(String message) { super(message);}
}
