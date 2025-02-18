package com.nextar.repository;

import com.nextar.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<UserEntity, Long> {
	
	UserDetails findByLogin(String login);

}
