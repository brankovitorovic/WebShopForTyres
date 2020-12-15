package bran.packages.cart.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import bran.packages.tyre.entity.Tyre;
import lombok.Data;

@Data
@Entity
@Table( name = "cartitems")
public class CartItem {
/*
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	*/
	
	@EmbeddedId
	private CartItemId id;

	@MapsId("shoping_cart_id")
	@ManyToOne
	@JoinColumn(name = "shoping_cart_id")
	private ShopingCart shopingCart;

	@MapsId("tyre_id")
	@ManyToOne
	@JoinColumn(name = "tyre_id")
	private Tyre tyre;
	
	@Column()
	private int quantity;
	
}

