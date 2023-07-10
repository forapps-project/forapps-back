package com.team.forapps.user.repository;

import com.team.forapps.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
