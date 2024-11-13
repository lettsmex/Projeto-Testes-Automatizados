package com.projetofinal5.ProjetoFinal5.controller;


import com.projetofinal5.ProjetoFinal5.dto.LoginResponseDTO;
import com.projetofinal5.ProjetoFinal5.dto.RegisterDTO;
import com.projetofinal5.ProjetoFinal5.dto.UserDTO;
import com.projetofinal5.ProjetoFinal5.entity.UserEntity;
import com.projetofinal5.ProjetoFinal5.service.TokenService;
import com.projetofinal5.ProjetoFinal5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDTO userDTO) {

        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDTO.login(), userDTO.password());

        var authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        String token = tokenService.generateToken((UserEntity) authentication.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO registerDTO) {

        if (Objects.nonNull(userService.findByLogin(registerDTO.login()))) {
            return ResponseEntity.badRequest().build();
        }
        userService.saveUser(registerDTO);
        return ResponseEntity.ok("Usuário Registrado");
    }
}