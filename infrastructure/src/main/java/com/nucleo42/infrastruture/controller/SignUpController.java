package com.nucleo42.infrastruture.controller;

import com.nucleo42.entity.User;
import com.nucleo42.exception.AcceptTermsException;
import com.nucleo42.exception.UserAlreadyExistsException;
import com.nucleo42.infrastruture.annotation.ApiRequestBody;
import com.nucleo42.infrastruture.dto.SignUpRequestDTO;
import com.nucleo42.usecase.AddAccount;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            description = "Create a new account",
            summary = "Sign up"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Account created",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(value = "User registered successfully")
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(value = "User already exists")
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(value = "Internal server error")
                    )
            )
    })
    @ApiRequestBody(
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                    {
                                        "firstName": "John",
                                        "lastName": "Doe",
                                        "email": "johndoe@mail.com",
                                        "password": "Password@1234",
                                        "acceptTerms": true
                                    }
                                    """
                    )
            )
    )
    @PostMapping
    public ResponseEntity<String> handle(@RequestBody @Valid SignUpRequestDTO dto) {
        try {
            var user = new User(null, dto.firstName(), dto.lastName(), dto.email(), dto.password(), "", dto.acceptTerms(), null);
            var result = this.usecase.add(user);
            return ResponseEntity.created(null).body(result);
        } catch (UserAlreadyExistsException | AcceptTermsException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body("Internal server error");
        }
    }
}
