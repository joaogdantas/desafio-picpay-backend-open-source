package com.picpay.joaogdantas.PicPaySimplificado.domain.service;

import com.picpay.joaogdantas.PicPaySimplificado.domain.exceptions.HasInsufficientFundsException;
import com.picpay.joaogdantas.PicPaySimplificado.domain.exceptions.StoreCannotInitiateTransfersException;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.User;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.Wallet;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto.CreateUserDTO;
import com.picpay.joaogdantas.PicPaySimplificado.domain.repository.UserRepository;
import com.picpay.joaogdantas.PicPaySimplificado.domain.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static com.picpay.joaogdantas.PicPaySimplificado.domain.model.PicPayUserType.COMUM;
import static com.picpay.joaogdantas.PicPaySimplificado.domain.model.PicPayUserType.LOJISTA;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
@SpringBootTest()
public class TransactionsServiceTest {

    @InjectMocks
    private TransactionsService transactionsService;
    @Mock
    private WalletRepository walletRepository;
    @Mock
    private UserRepository userRepository;
    private User payerUserTest;
    private User payeeUserTest;
    private User storeUserTest;

    @BeforeEach
    public void setUp() {
        Wallet payerWallet = new Wallet(100.0);
        Wallet payeeWallet = new Wallet(30.0);
        Wallet storeWallet = new Wallet(50.0);

        payerUserTest = new User(new CreateUserDTO("Luis", "1234567890", "luisf@gmail.com", "123", COMUM));
        payeeUserTest = new User(new CreateUserDTO("Jose", "1234567891", "josep@gmail.com", "123", COMUM));
        storeUserTest = new User(new CreateUserDTO("Marvin", "01234567890", "marving@gmail.com", "123", LOJISTA));

        payerUserTest.setWallet(payerWallet);
        payeeUserTest.setWallet(payeeWallet);
        storeUserTest.setWallet(storeWallet);
    }

    @DisplayName("Testa se a transferência tem o comportamento correto, em caso de sucesso.")
    @Test
    @Transactional
    public void testTransfer() {
        transactionsService.transfer(payerUserTest, payeeUserTest, 70.0);

        Double expectedPayerNewValue = 30.0;
        Double expectedPayeeNewValue = 100.0;

        assertThat(payerUserTest.getWallet().getBalance())
                .isEqualTo(expectedPayerNewValue);

        assertThat(payeeUserTest.getWallet().getBalance())
                .isEqualTo(expectedPayeeNewValue);
    }

    @Test
    @DisplayName("Testa o depósito adiciona saldo à carteira do usuário.")
    public void testDeposit() {
        transactionsService.deposit(payerUserTest, 50.0);

        Double expectedPayerNewValue = 150.0;

        assertThat(payerUserTest.getWallet().getBalance())
                .isEqualTo(expectedPayerNewValue);
    }

    @Test
    @DisplayName("Testa o saque retira saldo da carteira do usuário.")
    public void testWithdraw() {
        transactionsService.withdraw(payerUserTest, 50.0);

        Double expectedPayerNewValue = 50.0;

        assertThat(payerUserTest.getWallet().getBalance())
                .isEqualTo(expectedPayerNewValue);
    }

    @Test
    @DisplayName("Testa se o método de saque lança a exceção HasInsufficientFunds se tentar realizar o saque com saldo insuficiente.")
    public void testWithdrawInsufficientFunds() {
        assertThatThrownBy(() -> transactionsService.withdraw(payerUserTest, 150.0))
                .isInstanceOf(HasInsufficientFundsException.class)
                .hasMessage("Você não possui saldo suficiente para realizar a operação");
    }

    @Test
    @DisplayName("Testa se o método TransferIsValid lança uma exceção de StoreCannotInitiateTransfer, caso o pagador seja um lojista.")
    public void testStoreCannotInitiateTransfer() {
        assertThatThrownBy(() -> transactionsService.transferIsValid(storeUserTest, 50.0))
                .isInstanceOf(StoreCannotInitiateTransfersException.class)
                .hasMessage("Lojistas não podem realizar transferências");
    }

    @Test
    @DisplayName("Testa se o método TransferIsValid lança uma exceção de HasInsufficientFunds, caso o pagador não possua saldo suficiente.")
    public void testHasInsufficientFunds() {
        assertThatThrownBy(() -> transactionsService.transferIsValid(payerUserTest, 150.0))
                .isInstanceOf(HasInsufficientFundsException.class)
                .hasMessage("Você não possui saldo suficiente para realizar a operação");
    }
}


