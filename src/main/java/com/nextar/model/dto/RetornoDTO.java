package com.nextar.model.dto;

import com.nextar.model.entity.CalculoEntity;

import java.math.BigDecimal;

public record RetornoDTO(BigDecimal resultado) {

    public RetornoDTO(CalculoEntity entity){
        this(entity.getResultado().setScale(2));
    }

    public RetornoDTO(Double resultado){
        this(new BigDecimal(resultado).setScale(2));
    }
}
