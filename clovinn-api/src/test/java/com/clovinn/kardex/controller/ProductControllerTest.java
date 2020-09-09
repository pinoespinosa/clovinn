package com.clovinn.kardex.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.clovinn.kardex.dto.OrderDTO;
import com.clovinn.kardex.dto.ProductDTO;
import com.clovinn.kardex.dto.StockDTO;
import com.clovinn.kardex.exception.InvalidProductException;
import com.clovinn.kardex.exception.OutOfStockProductException;

@SpringBootTest
class ProductControllerTest {

	@Autowired
	private ProductController controller;

	@Test
	void contextLoads() {
		assertThat(getController()).isNotNull();
	}

	@Test
	void validProductTest() {
		ProductDTO p = getController().getProduct(1);
		assertThat(p).isNotNull();
	}

	@Test
	void validProductsListTest() {
		Page<ProductDTO> p = getController().getProducts(PageRequest.of(0, 8));
		assertThat(p).isNotNull();
		assertThat(p.getContent()).isNotEmpty();

	}

	@Test
	void invalidProductTest() {
		try {
			getController().getProduct(-5000);
			fail("Exception not thrown");
		} catch (InvalidProductException e) {
			assertThat(e).isNotNull();
		}
	}

	@Test
	void buyProductOk() {
		OrderDTO order = new OrderDTO();
		order.setAmmount(8);
		ProductDTO result = getController().buyProduct(1, order);
		assertThat(result).isNotNull();
		assertThat(result.getStock()).isNotNull();
		assertThat(result.getStock()).isEqualTo(2);
	}

	@Test
	void buyProductMoreThanStockOk() {
		try {
			OrderDTO order = new OrderDTO();
			order.setAmmount(888);
			getController().buyProduct(1, order);
			fail("Exception not thrown");
		} catch (OutOfStockProductException e) {
			assertThat(e).isNotNull();
		}
	}

	@Test
	void buyInexistentProduct() {
		try {
			OrderDTO order = new OrderDTO();
			order.setAmmount(1);
			getController().buyProduct(999, order);
			fail("Exception not thrown");

		} catch (InvalidProductException e) {
			assertThat(e).isNotNull();
		}
	}

	@Test
	void changeStockProductOk() {
		StockDTO order = new StockDTO();
		order.setAmmount(8);
		ProductDTO result = getController().changeStock(3, order);
		assertThat(result).isNotNull();
		assertThat(result.getStock()).isNotNull();
		assertThat(result.getStock()).isEqualTo(8);

	}

	@Test
	void changeStockInexistentProduct() {
		try {
			StockDTO order = new StockDTO();
			order.setAmmount(1);
			getController().changeStock(999, order);
			fail("Exception not thrown");

		} catch (InvalidProductException e) {
			assertThat(e).isNotNull();
		}
	}

	@Test
	void deleteProduct() {
		int prodId = 2;

		ProductDTO pBeforeDelete = getController().getProduct(prodId);
		getController().deleteProduct(prodId);
		assertThat(pBeforeDelete).isNotNull();
		try {
			getController().getProduct(prodId);
			fail("Exception not thrown");
		} catch (InvalidProductException e) {
			assertThat(e).isNotNull();
		}

	}

	public ProductController getController() {
		return controller;
	}

	public void setController(ProductController controller) {
		this.controller = controller;
	}
}
