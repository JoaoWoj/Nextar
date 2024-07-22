package com.nextar.repository;

import java.util.Optional;

import com.nextar.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByLogin(String login);

}
