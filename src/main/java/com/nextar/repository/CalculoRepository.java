package com.nextar.repository;

import com.nextar.model.entity.CalculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CalculoRepository extends JpaRepository<CalculoEntity, Long> {

    Optional<CalculoEntity> findByExpressao(String expressao);

}
