package com.nucleo42.infrastruture.controller;

import com.nucleo42.entity.User;
import com.nucleo42.exception.UserAlreadyExistsException;
import com.nucleo42.infrastruture.dto.SignUpRequestDTO;
import com.nucleo42.usecase.AddAccount;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignUpController {
    @Autowired
    private AddAccount usecase;

    @PostMapping
    public ResponseEntity<String> handle(@RequestBody @Valid SignUpRequestDTO dto) {
        try {
            var user = new User(dto.firstName(), dto.lastName(), dto.email(), dto.password(), "", dto.acceptTerms(), null);
            var result = this.usecase.add(user);
            return ResponseEntity.created(null).body(result);
        } catch (UserAlreadyExistsException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body("Internal server error");
        }
    }
}
