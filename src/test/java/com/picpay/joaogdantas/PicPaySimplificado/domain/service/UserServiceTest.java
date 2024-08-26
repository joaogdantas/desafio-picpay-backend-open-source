package com.picpay.joaogdantas.PicPaySimplificado.domain.service;

import com.picpay.joaogdantas.PicPaySimplificado.domain.exceptions.UserNotFoundException;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.User;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.Wallet;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto.CreateUserDTO;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto.ReturnPublicInformationUserDTO;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto.ReturnSensitiveInformationUserDTO;
import com.picpay.joaogdantas.PicPaySimplificado.domain.repository.UserRepository;
import com.picpay.joaogdantas.PicPaySimplificado.domain.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.picpay.joaogdantas.PicPaySimplificado.domain.model.PicPayUserType.COMUM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private WalletRepository walletRepository;

    private User user;

    private Wallet wallet;

    @BeforeEach
    public void setUp() {
        wallet = new Wallet(100.0);
        user = new User(new CreateUserDTO("Luis", "12345678901", "luisf@gmail.com", "password", COMUM));
        user.setWallet(wallet);
    }

    @DisplayName("Testa se o método saveUser cria e salva um novo usuário")
    @Test
    public void testSaveUser() {
        CreateUserDTO createUserDTO = new CreateUserDTO("Luis", "12345678901", "luisf@gmail.com", "password", COMUM);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.saveUser(createUserDTO);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getName()).isEqualTo("Luis");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @DisplayName("Testa se o método returnUserByName retorna um usuário com o nome correto")
    @Test
    public void testReturnUserByName() {
        when(userRepository.findByName("Luis")).thenReturn(Optional.of(user));

        User foundUser = userService.returnUserByName("Luis");

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getName()).isEqualTo("Luis");
    }

    @DisplayName("Testa se o método returnUserByName lança uma exceção quando o usuário não é encontrado")
    @Test
    public void testReturnUserByNameThrowsException() {
        when(userRepository.findByName("Luis")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.returnUserByName("Luis"))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("Usuário não encontrado com o nome Luis");
    }

    @DisplayName("Testa se o método findSensitiveInformationUserById retorna as informações sensíveis do usuário")
    @Test
    public void testFindSensitiveInformationUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        ReturnSensitiveInformationUserDTO dto = userService.findSensitiveInformationUserById(1L);

        assertThat(dto).isNotNull();
        assertThat(dto.name()).isEqualTo("Luis");
        assertThat(dto.balance()).isEqualTo(100.0);
    }

    @DisplayName("Testa se o método findSensitiveInformationUserById lança uma exceção quando o usuário não é encontrado")
    @Test
    public void testFindSensitiveInformationUserByIdThrowsException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.findSensitiveInformationUserById(1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Usuário não encontrado com o id 1");
    }

    @DisplayName("Testa se o método findPublicInformationUserById retorna as informações públicas do usuário")
    @Test
    public void testFindPublicInformationUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        ReturnPublicInformationUserDTO dto = userService.findPublicInformationUserById(1L);

        assertThat(dto).isNotNull();
        assertThat(dto.name()).isEqualTo("Luis");
    }

    @DisplayName("Testa se o método findPublicInformationUserById lança uma exceção quando o usuário não é encontrado")
    @Test
    public void testFindPublicInformationUserByIdThrowsException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.findPublicInformationUserById(1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Usuário não encontrado com o id 1");
    }

    @DisplayName("Testa se o método findAllSensitiveInformationUsers retorna todos os usuários com informações sensíveis")
    @Test
    public void testFindAllSensitiveInformationUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<ReturnSensitiveInformationUserDTO> dtos = userService.findAllSensitiveInformationUsers();

        assertThat(dtos).isNotEmpty();
        assertThat(dtos.get(0).name()).isEqualTo("Luis");
    }

    @DisplayName("Testa se o método findAllPublicInformationUsers retorna todos os usuários com informações públicas")
    @Test
    public void testFindAllPublicInformationUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<ReturnPublicInformationUserDTO> dtos = userService.findAllPublicInformationUsers();

        assertThat(dtos).isNotEmpty();
        assertThat(dtos.get(0).name()).isEqualTo("Luis");
    }

    @DisplayName("Testa se o método deleteUserById remove um usuário pelo id")
    @Test
    public void testDeleteUserById() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUserById(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @DisplayName("Testa se o método deleteUserByName remove um usuário pelo nome")
    @Test
    public void testDeleteUserByName() {
        doNothing().when(userRepository).deleteByName("Luis");

        userService.deleteUserByName("Luis");

        verify(userRepository, times(1)).deleteByName("Luis");
    }
}
