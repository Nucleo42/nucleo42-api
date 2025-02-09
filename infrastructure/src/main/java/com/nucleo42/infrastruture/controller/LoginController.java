package com.nucleo42.infrastruture.controller;

import com.nucleo42.exception.InvalidCredentialsException;
import com.nucleo42.infrastruture.dto.LoginRequestDTO;
import com.nucleo42.infrastruture.dto.LoginResponseDTO;
import com.nucleo42.usecase.Login;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private Login usecase;

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody @Valid LoginRequestDTO dto) {
            try {
                var token = this.usecase.login(dto.email(), dto.password());
                return ResponseEntity.ok(new LoginResponseDTO(token));
            } catch (InvalidCredentialsException ex) {
                return ResponseEntity.status(401).body(ex.getMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
                return ResponseEntity.status(500).body("Internal server error");
            }
    }
}
