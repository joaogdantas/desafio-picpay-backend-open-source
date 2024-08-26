package com.picpay.joaogdantas.PicPaySimplificado.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.PicPayUserType;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.User;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto.CreateUserDTO;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto.ReturnSensitiveInformationUserDTO;
import com.picpay.joaogdantas.PicPaySimplificado.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static com.picpay.joaogdantas.PicPaySimplificado.domain.model.PicPayUserType.COMUM;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private CreateUserDTO createUserDTO;
    private User newUser;
    private ReturnSensitiveInformationUserDTO returnSensitiveInformationUserDTO;

    @BeforeEach
    public void setUp() {
        createUserDTO = new CreateUserDTO("Luis", "1234567890", "luisf@gmail.com", "123", COMUM);
        newUser = new User(createUserDTO);
        returnSensitiveInformationUserDTO = new ReturnSensitiveInformationUserDTO(1, "Luis", "1234567890", "luisf@gmail.com", "senha123", PicPayUserType.COMUM, 0.0);
    }

    @Test
    @DisplayName("Testa a criação de um novo usuário com sucesso")
    public void createUser_success() throws Exception {
        when(userService.saveUser(any(CreateUserDTO.class))).thenReturn(newUser);

        mockMvc.perform(post("/api/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Usuário criado com sucesso!"));
    }

    @Test
    @DisplayName("Testa a obtenção de todos os usuários com informações sensíveis")
    public void getAllSensitiveInformationUsers_success() throws Exception {
        List<ReturnSensitiveInformationUserDTO> usersList = Arrays.asList(returnSensitiveInformationUserDTO);

        when(userService.findAllSensitiveInformationUsers()).thenReturn(usersList);

        mockMvc.perform(get("/api/users/getAll/sensitiveData")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(usersList)));
    }
}
