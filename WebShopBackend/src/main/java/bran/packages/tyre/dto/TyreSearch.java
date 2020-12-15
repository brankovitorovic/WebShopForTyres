package bran.packages.tyre.dto;

import bran.packages.tyre.enums.Season;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TyreSearch {

	private String width;
	
	private String height;
	
	private String diameter;
	
	private Season season;
	
}
