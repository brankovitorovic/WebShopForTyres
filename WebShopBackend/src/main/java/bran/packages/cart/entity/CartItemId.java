package bran.packages.cart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class CartItemId implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "shoping_cart_id")
	private Long shopingCartId;
	
	@Column(name = "tyre_id")
	private Long tyreId;
	
}
