package com.nextar.service;

import com.nextar.model.dto.EntradaDTO;
import com.nextar.model.dto.RetornoDTO;
import org.springframework.http.ResponseEntity;

public interface CalculoService {

    ResponseEntity<?> calcularExpressao(EntradaDTO dto);

}
