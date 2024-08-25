package com.picpay.joaogdantas.PicPaySimplificado.domain.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Usuário não encontrado, por favor insira um válido");
    }

    public UserNotFoundException(String message) { super(message);}

}
