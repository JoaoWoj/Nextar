package com.nextar.model.dto;

import com.nextar.model.entity.CalculoEntity;

public record RetornoDTO(Double resultado) {

    public RetornoDTO(CalculoEntity entity){
        this(entity.getResultado());
    }
}
