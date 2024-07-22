package com.nextar.service.Impl;

import java.util.Optional;

import com.nextar.infra.security.TokenService;
import com.nextar.model.dto.AuthDTO;
import com.nextar.model.dto.LoginResponseDTO;
import com.nextar.model.entity.UserEntity;
import com.nextar.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;

	@Override
	public ResponseEntity<LoginResponseDTO> login(AuthDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		UserEntity user = (UserEntity) auth.getPrincipal();
		var token = tokenService.generateToken(user);
		return ResponseEntity.ok().body(new LoginResponseDTO(token, user.getRole().toString(), user.getId(), user.getLogin()));
	}

}
