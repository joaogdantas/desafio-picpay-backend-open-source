package com.picpay.joaogdantas.PicPaySimplificado.controller;

import com.picpay.joaogdantas.PicPaySimplificado.domain.model.User;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto.CreateUserDTO;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto.ReturnSensitiveInformationUserDTO;
import com.picpay.joaogdantas.PicPaySimplificado.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody CreateUserDTO createUserDTO) {
        User newUser = userService.saveUser(createUserDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso!");
    }

    @GetMapping("/getAll/sensitiveData")
    public ResponseEntity<List<ReturnSensitiveInformationUserDTO>> getAllSensitiveInformationUsers() {
        List<ReturnSensitiveInformationUserDTO> usersList = userService.findAllSensitiveInformationUsers();
        return ResponseEntity.ok(usersList);
    }

}
