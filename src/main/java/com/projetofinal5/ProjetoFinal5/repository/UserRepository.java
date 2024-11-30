package com.projetofinal5.ProjetoFinal5.repository;

import com.projetofinal5.ProjetoFinal5.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserDetails findByLogin(String login);
}
