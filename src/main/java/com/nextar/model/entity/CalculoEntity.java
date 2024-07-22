package com.nextar.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "expressions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull
    private String expressao;

    @NotBlank
    @NotNull
    @Column(scale=2)
    private Double resultado;

    public CalculoEntity(String expressao, Double resultado){
        this.expressao = expressao;
        this.resultado = resultado;
    }

}
