package com.nextar.service;

import com.nextar.model.dto.CadastroDTO;
import com.nextar.model.entity.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> findAll();
    ResponseEntity<?> create(CadastroDTO data);
    ResponseEntity<UserEntity> findById(Long id);
    ResponseEntity<UserEntity> update(Long id, UserEntity user);
    ResponseEntity<?> delete(Long id);
}
