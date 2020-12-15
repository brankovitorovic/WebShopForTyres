package bran.packages.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bran.packages.cart.entity.ShopingCart;

public interface ShopingCartRepository extends JpaRepository<ShopingCart, Long>{

	Optional<ShopingCart> findByUser(String user);
	
}
