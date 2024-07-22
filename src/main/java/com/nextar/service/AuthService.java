package com.nextar.service;

import com.nextar.model.dto.AuthDTO;
import com.nextar.model.dto.LoginResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<LoginResponseDTO> login(AuthDTO data);

}
