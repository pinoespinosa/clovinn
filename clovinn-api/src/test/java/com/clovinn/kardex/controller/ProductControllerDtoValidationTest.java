package com.clovinn.kardex.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.clovinn.kardex.dto.OrderDTO;
import com.clovinn.kardex.dto.StockDTO;
import com.clovinn.kardex.service.IProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
class ProductControllerDtoValidationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IProductService productService;

	private final ObjectMapper mapper = new ObjectMapper();

	/**
	 *  ****************** To buy tests ******************
	 */
	@Test
	void buyNegativeAmmount() throws JsonProcessingException, Exception {

		OrderDTO order = new OrderDTO();
		order.setAmmount(-5);

		Exception e = mockMvc
				.perform(post("/product/1/buy").content(mapper.writeValueAsString(order)).contentType(APPLICATION_JSON))
				.andExpect(status().is4xxClientError()).andReturn().getResolvedException();
		assertThat(e).isInstanceOf(MethodArgumentNotValidException.class);
	}

	@Test
	void buyAmmountOk() throws JsonProcessingException, Exception {

		OrderDTO order = new OrderDTO();
		order.setAmmount(5);
		mockMvc.perform(post("/product/1/buy").content(mapper.writeValueAsString(order)).contentType(APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	/**
	 *  ****************** To change stock tests ******************
	 */
	@Test
	void changeStockFail() throws JsonProcessingException, Exception {

		StockDTO order = new StockDTO();
		order.setAmmount(-5);

		Exception e = mockMvc
				.perform(post("/product/1/stock").content(mapper.writeValueAsString(order)).contentType(APPLICATION_JSON))
				.andExpect(status().is4xxClientError()).andReturn().getResolvedException();
		assertThat(e).isInstanceOf(MethodArgumentNotValidException.class);
	}

	@Test
	void changeStockOk() throws JsonProcessingException, Exception {

		StockDTO order = new StockDTO();
		order.setAmmount(5);
		mockMvc.perform(post("/product/1/stock").content(mapper.writeValueAsString(order)).contentType(APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
}
