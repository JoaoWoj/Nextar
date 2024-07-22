package com.nextar.controller;

import com.nextar.model.dto.EntradaDTO;
import com.nextar.service.CalculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CalculoController {

    @Autowired
    CalculoService service;

    @PostMapping("/calculo")
    public ResponseEntity<?> calculo(@Valid @RequestBody EntradaDTO dto){
        return this.service.calcularExpressao(dto);
    }

}
