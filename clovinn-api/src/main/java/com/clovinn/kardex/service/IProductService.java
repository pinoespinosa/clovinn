package com.clovinn.kardex.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.clovinn.kardex.dto.OrderDTO;
import com.clovinn.kardex.dto.ProductDTO;
import com.clovinn.kardex.dto.StockDTO;

public interface IProductService {

	/**
	 * Create a product and return its DTO.
	 * @param req
	 * @return
	 */
	ProductDTO createProduct(ProductDTO req);

	/**
	 * Returns all products paginated
	 * @param pageable
	 * @return
	 */
	Page<ProductDTO> getAllProduct(Pageable pageable);

	/**
	 * Return the DTO of a specific product
	 * @param id
	 * @return
	 */
	ProductDTO getProduct(Integer id);

	/**
	 * Makes a sell checking at first the product exist and the ammount
	 * in the order is smaller than stock;
	 * @param id
	 * @param order
	 * @return
	 */
	ProductDTO buyProduct(Integer id, OrderDTO order);

	/**
	 * Changes the stock for an specific product.
	 * @param id
	 * @param stock
	 * @return
	 */
	ProductDTO changeStock(Integer id, StockDTO stock);

	/**
	 * Remove a product.
	 * @param id
	 */
	void deleteProduct(Integer id);

	/**
	 * Update product and return its DTO.
	 * @param id
	 * @param req
	 * @return
	 */
	ProductDTO updateProduct(Integer id, @Valid ProductDTO req);

}
