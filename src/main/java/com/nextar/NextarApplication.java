package com.nextar;

import com.nextar.model.entity.UserEntity;
import com.nextar.model.enumerator.UserRoleEnum;
import com.nextar.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NextarApplication {

	public static void main(String[] args) {
		SpringApplication.run(NextarApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository){
		return args -> {
			userRepository.deleteAll();
			UserEntity user = new UserEntity();
			user.setLogin("admin");
			user.setPassword("$2a$10$WIO3eqVnm6NjqQ74W618XO37GOHXmGM4zMr1HPkReUA/YkIssSb4W");//admin
			user.setRole(UserRoleEnum.ADMIN);
			userRepository.save(user);
		};
	}

}
