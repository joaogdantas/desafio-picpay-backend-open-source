package com.picpay.joaogdantas.PicPaySimplificado.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.User;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto.CreateUserDTO;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto.DepositDTO;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto.TransferDTO;
import com.picpay.joaogdantas.PicPaySimplificado.domain.service.TransactionsService;
import com.picpay.joaogdantas.PicPaySimplificado.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.picpay.joaogdantas.PicPaySimplificado.domain.model.PicPayUserType.COMUM;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionsController.class)
public class TransactionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionsService transactionsService;

    @MockBean
    private UserService userService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Testa se o endpoint funciona corretamente para um caso bem sucedido")
    public void testTransferSuccess() throws Exception {
        TransferDTO transferDTO = new TransferDTO("Luis", "Jose", 50.0);

        User payer = new User(new CreateUserDTO("Luis", "1234567890", "luisf@gmail.com", "123", COMUM));
        User payee = new User(new CreateUserDTO("Jose", "1234567891", "josep@gmail.com", "123", COMUM));

        when(userService.returnUserByName("Luis")).thenReturn(payer);
        when(userService.returnUserByName("Jose")).thenReturn(payee);
        doNothing().when(transactionsService).transfer(any(User.class), any(User.class), any(Double.class));

        mockMvc.perform(post("/api/transaction/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Transferência realizada com sucesso!"));
    }

    @Test
    @DisplayName("Testa se o endpoint de depósito funciona corretamente para um caso bem sucedido")
    public void testDepositSuccess() throws Exception {
        DepositDTO depositDTO = new DepositDTO("Luis", 100.0);

        User user = new User(new CreateUserDTO("Luis", "1234567890", "luisf@gmail.com", "123", COMUM));

        when(userService.returnUserByName("Luis")).thenReturn(user);
        doNothing().when(transactionsService).deposit(any(User.class), any(Double.class));

        mockMvc.perform(post("/api/transaction/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(depositDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Depósito realizado com sucesso!"));
    }
}
