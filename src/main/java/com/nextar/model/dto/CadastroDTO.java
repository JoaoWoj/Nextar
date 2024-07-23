package com.nextar.model.dto;

import com.nextar.model.enumerator.UserRoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroDTO(@NotBlank(message = "Login não pode ser vazio")
                          @NotNull(message = "Login não pode ser vazio")
                          String login,
                          @NotBlank(message = "Password não pode ser vazio")
                          @NotNull(message = "Password não pode ser vazio")
                          String password,
                          @NotNull(message = "Role não pode ser vazia")
                          UserRoleEnum role) {

}
