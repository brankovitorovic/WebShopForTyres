package bran.packages.cart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bran.packages.cart.entity.CartItem;
import bran.packages.cart.entity.CartItemId;
import bran.packages.cart.entity.ShopingCart;

public interface CartItemRepository extends JpaRepository<CartItem, CartItemId>{

	Optional<List<CartItem>> findByShopingCart(ShopingCart shopingCart);
	
	void deleteByShopingCart(ShopingCart shopingCart);
	
}
