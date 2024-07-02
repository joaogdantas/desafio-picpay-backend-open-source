package com.picpay.joaogdantas.PicPaySimplificado.domain.service;

import com.picpay.joaogdantas.PicPaySimplificado.domain.model.User;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.Wallet;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto.CreateUserDTO;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto.ReturnPublicInformationUserDTO;
import com.picpay.joaogdantas.PicPaySimplificado.domain.model.dto.ReturnSensitiveInformationUserDTO;
import com.picpay.joaogdantas.PicPaySimplificado.domain.repository.UserRepository;
import com.picpay.joaogdantas.PicPaySimplificado.domain.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private Wallet wallet;
    @Autowired
    private WalletRepository walletRepository;
    private User user;
    @Autowired
    private UserRepository userRepository;

    public User saveUser(CreateUserDTO createUserDTO){
        User newUser = new User(createUserDTO);
        return userRepository.save(newUser);
    }

    public User returnUserByName(String name){
        return userRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o nome " + name));
    }

    public ReturnSensitiveInformationUserDTO findSensitiveInformationUserById(Long id){
        return userRepository.findById(id)
                .map(personData -> new ReturnSensitiveInformationUserDTO(personData.getId(), personData.getName(), personData.getCpf(), personData.getEmail(), personData.getPassword(), personData.getUserType()))
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o id " + id));
    }

    public ReturnPublicInformationUserDTO findPublicInformationUserById(Long id){
        return userRepository.findById(id)
                .map(personData -> new ReturnPublicInformationUserDTO(personData.getName(), personData.getEmail(), personData.getUserType()))
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o id " + id));
    }

    public List<ReturnSensitiveInformationUserDTO> findAllSensitiveInformationUsers(){
        return userRepository.findAll()
                .stream()
                .map(personData -> new ReturnSensitiveInformationUserDTO(personData.getId(), personData.getName(), personData.getCpf(), personData.getEmail(), personData.getPassword(), personData.getUserType()))
                .collect(Collectors.toList());
    }

    public List<ReturnPublicInformationUserDTO> findAllPublicInformationUsers(){
        return userRepository.findAll()
                .stream()
                .map(personData -> new ReturnPublicInformationUserDTO(personData.getName(), personData.getEmail(), personData.getUserType()))
                .collect(Collectors.toList());
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public void deleteUserByName(String name){
        userRepository.deleteByName(name);
    }
}
