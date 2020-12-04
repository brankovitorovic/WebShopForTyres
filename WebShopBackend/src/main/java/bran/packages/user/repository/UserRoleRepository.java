package bran.packages.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bran.packages.user.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findByName(String name);
	
}
