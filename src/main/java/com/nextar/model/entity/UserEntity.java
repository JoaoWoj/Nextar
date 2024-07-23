package com.nextar.model.entity;

import static com.nextar.model.enumerator.UserRoleEnum.ADMIN;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nextar.model.enumerator.UserRoleEnum;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class UserEntity implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@NonNull
	@NotBlank
	@Length(max = 20)
	@Column(unique = true, nullable = false, length = 20)
	private String login;
	
	@NotNull
	@NonNull
	@Column(nullable = false)
	private String password;
	
	@NotNull
	@NonNull
	@Column(nullable = false)
	private UserRoleEnum role;

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.role == ADMIN) {
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
		} else {
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));
		}
	}

	@JsonIgnore
	@Override
	public String getUsername() {
		return login;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}
}
