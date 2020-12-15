package bran.packages.cart.dto;

import javax.validation.constraints.NotNull;

import bran.packages.tyre.dto.TyreDTO;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CartItemDTO {

	@NotNull
	private TyreDTO tyre;
	
	@NotNull
	private int quantity;
	
}
