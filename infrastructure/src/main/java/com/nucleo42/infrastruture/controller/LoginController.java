package com.nucleo42.infrastruture.controller;

import com.nucleo42.exception.InvalidCredentialsException;
import com.nucleo42.infrastruture.annotation.ApiRequestBody;
import com.nucleo42.infrastruture.dto.LoginRequestDTO;
import com.nucleo42.infrastruture.dto.LoginResponseDTO;
import com.nucleo42.usecase.Login;
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
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private Login usecase;

    @Operation(
            description = "Login to the system",
            summary = "Login"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Login successful",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = """
                                    {
                                        "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
                                    }
                                    """)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(value = "Invalid credentials")
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
                                        "email": "johndoe@mail.com",
                                        "password": "Password@1234"
                                    }
                                    """
                    )
            )
    )
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
