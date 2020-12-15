package bran.packages.tyre.dto;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import bran.packages.tyre.enums.Season;
import lombok.Data;

@Data
public class TyreDTO {

	@NotBlank
	private UUID frontID;
	
	@NotBlank
	private String brand;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String width;
	
	@NotBlank
	private String height;
	
	@NotBlank
	private String diameter;
	
	@NotBlank
	private double price;
	
	@NotBlank
	private Season season;
	
}
