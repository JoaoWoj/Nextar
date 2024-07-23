package com.nextar.service.Impl;

import com.nextar.model.dto.CadastroDTO;
import com.nextar.model.entity.UserEntity;
import com.nextar.repository.UserRepository;
import com.nextar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> findAll() {
        try{
            return userRepository.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public ResponseEntity<?> create(CadastroDTO data) {
        try {
            Optional<UserEntity> login = this.userRepository.findByLogin(data.login());
            if(login.isPresent()) {
                return ResponseEntity.badRequest().body("Login Já existe!");
            }
            UserEntity newUser = new UserEntity(data.login(), encriptarSenha(data.password()), data.role());

            return  ResponseEntity.ok().body(this.userRepository.save(newUser));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<UserEntity> findById(Long id) {
        try {
            Optional<UserEntity> user = userRepository.findById(id);
            return user.map(item -> ResponseEntity.ok().body(item)).orElse(ResponseEntity.notFound().build());
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }


    @Override
    public ResponseEntity<UserEntity> update(Long id, UserEntity usuario) {
        try {
            Optional<UserEntity> usuarioSalvo =  userRepository.findById(id);
            return usuarioSalvo.map(item ->{
                item.setLogin(usuario.getLogin());
                item.setPassword(encriptarSenha(usuario.getPassword()));
                item.setRole(usuario.getRole());
                return ResponseEntity.ok().body(userRepository.save(item));
            }).orElse(ResponseEntity.notFound().build());
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        try {
            return userRepository.findById(id)
                    .map(item -> {
                        userRepository.deleteById(id);
                        return ResponseEntity.noContent().build();
                    }).orElse(ResponseEntity.notFound().build());
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    private String encriptarSenha(String password){
        return new BCryptPasswordEncoder().encode(password);
    }
}
