package com.clovinn.kardex.dto;
import javax.validation.constraints.Min;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class OrderDTO {

	@NotNull
    @Min(1)
	private Integer ammount;

}
