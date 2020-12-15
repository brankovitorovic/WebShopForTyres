package bran.packages.tyre.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import bran.packages.tyre.enums.Season;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "tyres")
public class Tyre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "BINARY(16)")
	private UUID frontID;
	
	@Column(length = 50)
	@NotNull
	private String brand;
	
	@Column(length = 50)
	@NotNull
	private String name;

	@Column(length = 5)
	@NotNull
	private String width;
	
	@Column(length = 5)
	@NotNull
	private String height;
	
	@Column(length = 5)
	@NotNull
	private String diameter;
	
	@Column()
	@NotNull
	private double price;
	
	@Column()
	private Season season;
	
}
