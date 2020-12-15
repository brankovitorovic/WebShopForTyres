package bran.packages.cart.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import bran.packages.cart.dto.CartItemDTO;
import bran.packages.cart.entity.CartItem;
import bran.packages.cart.entity.CartItemId;
import bran.packages.cart.entity.ShopingCart;
import bran.packages.cart.exception.EmptyCartException;
import bran.packages.cart.exception.NoSpecificTyreInDatabase;
import bran.packages.cart.mapper.CartMapper;
import bran.packages.cart.repository.CartItemRepository;
import bran.packages.cart.repository.ShopingCartRepository;
import bran.packages.tyre.dto.TyreDTO;
import bran.packages.tyre.repository.TyreRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

	private final CartItemRepository cartItemRepository;
	
	private final ShopingCartRepository shopingCartRepository;
	
	private final CartMapper cartMapper;
	
	private final TyreRepository tyreRepository;
	
	@Transactional
	public CartItemDTO save(CartItemDTO cartItemDTO) {
				
		CartItem cartItem = cartMapper.fromDTO(cartItemDTO);
		
		cartItem.setId(getCartItemId(cartItemDTO.getTyre()));
		
		cartItem.setShopingCart(getShopingCart());
		
		cartItem.setTyre(tyreRepository.findByFrontID(cartItemDTO.getTyre().getFrontID()).get());
		
		return cartMapper.toDTO(cartItemRepository.save(cartItem));
		
	}
	
	@Transactional
	public CartItemDTO delete(CartItemDTO cartItemDTO) {
		
		
		CartItem cartItem = cartItemRepository.findById(getCartItemId(cartItemDTO.getTyre())).get();
		
		try {
			cartItemRepository.delete(cartItem);
		}catch (Exception e) {
			throw new NoSpecificTyreInDatabase("No specific tyre in database founded!");
		}
		return cartItemDTO;
	
	}
	
	public Boolean buy(List<CartItemDTO> cartItemDTOs) {
		
		try {
			cartItemDTOs.stream().forEach(tyreDTO -> delete(tyreDTO));
		}catch (Exception e) {
			throw new NoSpecificTyreInDatabase("No specific tyre in database founded!");
		}
		
		return Boolean.TRUE;
	}
	
 	public List<CartItemDTO> findAll(){
		
		Optional<List<CartItem>> optional = cartItemRepository.findByShopingCart(getShopingCart());
		
		if(optional.isEmpty()) {
			throw new EmptyCartException("You cart is empty!");
		}
		
		return cartMapper.toDTOList(optional.get());
	}
	
	private ShopingCart getShopingCart() {
		
		ShopingCart shopingCart;

		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Optional<ShopingCart> optional = shopingCartRepository.findByUser(username);		
		
		if(optional.isEmpty()){
			shopingCart = new ShopingCart();
			shopingCart.setUser(username);
			shopingCart = shopingCartRepository.save(shopingCart);
		}else {
			shopingCart = optional.get();
		}
		
		
		return shopingCart;
	}
	
	private CartItemId getCartItemId(TyreDTO tyreDTO) {

		CartItemId id = new CartItemId();
		
		id.setShopingCartId(getShopingCart().getShopingCartId());
		
		id.setTyreId(tyreRepository.findByFrontID(tyreDTO.getFrontID()).get().getId());
				
		return id;
	}


	
}
