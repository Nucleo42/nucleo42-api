package com.nucleo42.infrastructure.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateProjectRequestDTO (
        @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
        @NotBlank(message = "O nome do projeto precisa ser informado")
        String name,

        @NotBlank(message = "A descrição do projeto precisa ser informado")
        String description,

        @NotNull(message = "As vagas do projeto precisam ser informadas")
        @Min(value = 0, message = "O total de vagas deve ser um número natural")
        Integer vacancies,

        @NotBlank(message = "O objetivo do projeto precisa ser informado")
        String goal
){
}
