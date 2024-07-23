package com.nextar.service.Impl;

import com.nextar.model.dto.EntradaDTO;
import com.nextar.model.dto.RetornoDTO;
import com.nextar.model.entity.CalculoEntity;
import com.nextar.repository.CalculoRepository;
import com.nextar.service.CalculoService;
import com.nextar.util.CalculoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class CalculoServiceImpl implements CalculoService {

    @Autowired
    CalculoRepository repository;

    @Autowired
    CalculoUtils utils;

    @Override
    public ResponseEntity<?> calcularExpressao(EntradaDTO dto) {
        try {
            String expressao = dto.expressao();
            Optional<CalculoEntity> optionalCalculo = this.repository.findByExpressao(expressao);
            if (optionalCalculo.isPresent()) {
                return ResponseEntity.ok(new RetornoDTO(optionalCalculo.get().getResultado()));
            }
            BigDecimal resultado = BigDecimal.valueOf(this.utils.realizaCalculo(expressao)).setScale(2, RoundingMode.HALF_UP);
            gravarDadosBanco(expressao, resultado);
            return ResponseEntity.ok(new RetornoDTO(resultado));
        } catch (ArithmeticException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private void gravarDadosBanco(String expressao, BigDecimal resultado){
        try{
            CalculoEntity entity = new CalculoEntity(expressao, resultado);
            this.repository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gravar dados no banco! Erro: " + e.getMessage());
        }
    }
}
