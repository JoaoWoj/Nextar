package com.nextar.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EntradaDTO(@NotBlank(message = "Expressão não pode ser vazia")
                         @NotNull(message = "Expressão não pode ser nula")
                         String expressao) {

}
