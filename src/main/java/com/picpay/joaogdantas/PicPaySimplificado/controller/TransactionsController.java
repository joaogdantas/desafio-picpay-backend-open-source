package com.picpay.joaogdantas.PicPaySimplificado.controller;

import com.picpay.joaogdantas.PicPaySimplificado.domain.model.User;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto.CreateUserDTO;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto.DepositDTO;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto.TransferDTO;
import com.picpay.joaogdantas.PicPaySimplificado.domain.service.UserService;
import com.picpay.joaogdantas.PicPaySimplificado.domain.service.TransactionsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
public class TransactionsController {
    @Autowired
    private TransactionsService transactionsService;
    @Autowired
    private UserService userService;

    @PostMapping("/transfer")
    public ResponseEntity transfer(@RequestBody TransferDTO transferData) {

        User payer = userService.returnUserByName(transferData.payerName());
        User payee = userService.returnUserByName(transferData.payeeName());

        transactionsService.transfer(payer, payee, transferData.value());

        return ResponseEntity.status(HttpStatus.OK.value()).body("Transferência realizada com sucesso!");
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody DepositDTO depositDTO) {

            User user = userService.returnUserByName(depositDTO.Name());

            transactionsService.deposit(user, depositDTO.value());

            return ResponseEntity.ok("Depósito realizado com sucesso!");

    }
}
