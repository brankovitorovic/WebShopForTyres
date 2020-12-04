package bran.packages.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bran.packages.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByUsername(String username);

    void deleteByUsername(String username);
    
    @Query("select id from User u where u.username = ?1 ")
    Long findId(String username);
    
}
