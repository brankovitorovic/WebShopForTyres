package bran.packages.cart.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "shopingcarts")
public class ShopingCart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shopingCartId;
	
	@Column(unique = true)
	private String user;		
	
}
