package bran.packages.tyre.dto;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TyreDimensions {

	private List<String> widths;
	
	private List<String> heights;
	
	private List<String> diameters;
		
}
