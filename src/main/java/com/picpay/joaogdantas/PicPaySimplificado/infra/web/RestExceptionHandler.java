package com.picpay.joaogdantas.PicPaySimplificado.infra.web;

import com.picpay.joaogdantas.PicPaySimplificado.domain.exceptions.HasInsufficientFundsException;
import com.picpay.joaogdantas.PicPaySimplificado.domain.exceptions.StoreCannotInitiateTransfersException;
import com.picpay.joaogdantas.PicPaySimplificado.domain.exceptions.TransferIsInvalidException;
import com.picpay.joaogdantas.PicPaySimplificado.domain.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TransferIsInvalidException.class)
    private ResponseEntity<RestErrorMessage> IvalidTransferHandler(TransferIsInvalidException transferIsInvalidException){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, transferIsInvalidException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(restErrorMessage);
    }

    @ExceptionHandler(StoreCannotInitiateTransfersException.class)
    private ResponseEntity<RestErrorMessage> StoreCannotTransferHandler(StoreCannotInitiateTransfersException storeCannotInitiateTransfersException){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, storeCannotInitiateTransfersException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(restErrorMessage);
    }
    @ExceptionHandler(HasInsufficientFundsException.class)
    private ResponseEntity<RestErrorMessage> HasInsufficientFundsHandler(HasInsufficientFundsException hasInsufficientFundsException){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, hasInsufficientFundsException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(restErrorMessage);
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<RestErrorMessage> userNotFoundHandler(UserNotFoundException userNotFoundException){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, userNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(restErrorMessage);
    }
}
