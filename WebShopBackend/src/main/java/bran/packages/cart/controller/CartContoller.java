package bran.packages.cart.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bran.packages.cart.dto.CartItemDTO;
import bran.packages.cart.service.CartService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("cart")
public class CartContoller {

	private final CartService cartService;
	
	@GetMapping
	public ResponseEntity<List<CartItemDTO>> findAll(){
		return new ResponseEntity<>(cartService.findAll(),HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<CartItemDTO> save(@Valid @RequestBody CartItemDTO cartItemDTO){
		return new ResponseEntity<>(cartService.save(cartItemDTO),HttpStatus.OK);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<CartItemDTO> delete(@Valid @RequestBody CartItemDTO cartItemDTO){
		return new ResponseEntity<>(cartService.delete(cartItemDTO),HttpStatus.OK);
	}
	
	@PostMapping("/buy")
	public ResponseEntity<Boolean> buy(@Valid @RequestBody List<CartItemDTO> cartItemDTOs){
		return new ResponseEntity<>(cartService.buy(cartItemDTOs),HttpStatus.OK);
	}
	
	//TODO delete i buy
}
