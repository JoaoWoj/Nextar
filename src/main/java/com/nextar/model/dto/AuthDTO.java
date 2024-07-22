package com.nextar.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthDTO(@NotBlank(message = "Login não pode ser vazio")
                      @NotNull(message = "Login não pode ser vazio")
                      String login,
                      @NotBlank(message = "Password não pode ser vazio")
                      @NotNull(message = "Password não pode ser vazio")
                      String password) {

}
