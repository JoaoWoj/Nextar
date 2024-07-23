package com.nextar.model.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRoleEnum {

    ADMIN("admin"),
    USER("user");

    private String role;

}
