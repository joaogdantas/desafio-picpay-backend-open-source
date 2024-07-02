package com.picpay.joaogdantas.PicPaySimplificado.domain.repository;

import com.picpay.joaogdantas.PicPaySimplificado.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    void deleteByName(String name);
    Optional<User> findByName(String name);
}
