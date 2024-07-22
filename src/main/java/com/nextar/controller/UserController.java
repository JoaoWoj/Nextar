package com.nextar.controller;

import com.nextar.model.dto.CadastroDTO;
import com.nextar.model.entity.UserEntity;
import com.nextar.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/usuario")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserEntity> findAll() {
        return this.userService.findAll();
    }

    @PostMapping("/cadastro")
    @ResponseStatus(code = CREATED)
    public ResponseEntity<?> create(@RequestBody @Valid CadastroDTO data) {
        return this.userService.create(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> findById(@PathVariable Long id) {
        return this.userService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> update(@PathVariable Long id, @RequestBody @Valid UserEntity user) {
        return this.userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return this.userService.delete(id);
    }
}
