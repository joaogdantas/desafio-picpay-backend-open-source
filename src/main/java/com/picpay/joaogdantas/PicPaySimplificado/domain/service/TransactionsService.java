package com.picpay.joaogdantas.PicPaySimplificado.domain.service;

import com.picpay.joaogdantas.PicPaySimplificado.domain.exceptions.HasInsufficientFundsException;
import com.picpay.joaogdantas.PicPaySimplificado.domain.exceptions.StoreCannotInitiateTransfersException;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.User;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.Wallet;
import com.picpay.joaogdantas.PicPaySimplificado.domain.repository.UserRepository;
import com.picpay.joaogdantas.PicPaySimplificado.domain.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.picpay.joaogdantas.PicPaySimplificado.domain.model.PicPayUserType.LOJISTA;

@Service
public class TransactionsService {

    @Autowired
    private UserRepository userRepository;

    public void transfer(User payer, User payee, Double value) {

        transferIsValid(payer, value);

        var payerBalance = payer.getWallet().getBalance();
        var payeeBalance = payee.getWallet().getBalance();

        payer.getWallet().setBalance(payerBalance - value);
        payee.getWallet().setBalance(payeeBalance + value);
        userRepository.save(payer);
        userRepository.save(payee);
    }

    public void deposit(User user, Double value){

            var userWallet = user.getWallet();
            var userBalance = user.getWallet().getBalance();
            var newBalance = userBalance + value;

            userWallet.setBalance(newBalance);
            userRepository.save(user);

    }

    public void withdraw(User user, Double value){

            transferIsValid(user, value);

            var userWallet = user.getWallet();
            var userBalance = user.getWallet().getBalance();

            var newBalance = userBalance - value;

            userWallet.setBalance(newBalance);
            userRepository.save(user);
    }

    public boolean transferIsValid(User payer, Double operationValue) {
        if (!hasSufficientFunds(payer.getWallet().getBalance(), operationValue)) {
            throw new HasInsufficientFundsException();
        }

        if (isStore(payer)) {
            throw new StoreCannotInitiateTransfersException();
        }

        return true;
    }

    public boolean hasSufficientFunds(Double balance, Double operationValue) {
        return balance >= operationValue;
    }

    public boolean isStore(User user){
        return user.getUserType() == LOJISTA;
    }

}
