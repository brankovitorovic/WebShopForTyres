package bran.packages.tyre.pagination;

import bran.packages.tyre.dto.TyreSearch;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Pagination {

	private int page; // which page front wants
	
	private TyreSearch tyreSearch;
	
	private double priceFrom;
	
	private double priceTo;
	
	private String sort; 
	
}
