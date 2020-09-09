package com.clovinn.kardex.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class ProductDTO {
	private Integer id;
	
	@NotNull
    @Min(0)
	private Integer stock;
	
	@NotNull
    @Min(0)
	private Float price;
	
	@NotNull
	@Size(min=0, max = 250)
	private String image;

	@NotNull
	@Size(min=0, max = 250)
	private String name;
	
}
