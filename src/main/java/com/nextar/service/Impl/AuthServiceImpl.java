package com.nextar.service.Impl;

import com.nextar.infra.security.TokenService;
import com.nextar.model.dto.AuthDTO;
import com.nextar.model.dto.LoginResponseDTO;
import com.nextar.model.entity.UserEntity;
import com.nextar.repository.AuthRepository;
import com.nextar.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {

	private final AuthenticationManager authenticationManager;

	public AuthServiceImpl(@Lazy AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Autowired
	private TokenService tokenService;

	@Autowired
	AuthRepository repository;

	@Override
	public ResponseEntity<LoginResponseDTO> login(AuthDTO data) {
		try {
			var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
			var auth = this.authenticationManager.authenticate(usernamePassword);
			UserEntity user = (UserEntity) auth.getPrincipal();
			var token = tokenService.generateToken(user);
			return ResponseEntity.ok().body(new LoginResponseDTO(token, user.getRole().toString(), user.getId(), user.getLogin()));
		} catch (Exception e){
			return ResponseEntity.status(403).build();
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByLogin(username);
	}
}
