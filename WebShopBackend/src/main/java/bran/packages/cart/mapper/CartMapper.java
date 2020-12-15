package bran.packages.cart.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import bran.packages.cart.dto.CartItemDTO;
import bran.packages.cart.entity.CartItem;

@Mapper(componentModel = "spring")
public interface CartMapper {

	CartItemDTO toDTO(CartItem cartItem);
	
	CartItem fromDTO(CartItemDTO cartItemDTO);
	
	List<CartItemDTO> toDTOList(List<CartItem> cartItems);
	
}
