package com.clovinn.kardex.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.clovinn.kardex.dto.OrderDTO;
import com.clovinn.kardex.dto.ProductDTO;
import com.clovinn.kardex.dto.StockDTO;
import com.clovinn.kardex.service.IProductService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/product")
public class ProductController {

	/**
	 * TODO - - Agregar logg a los metodos
	 */

	protected final static int DEFAULT_PAGE_SIZE = 8;

	@Autowired
	private IProductService productService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	@ResponseBody
	public ProductDTO createProduct(@Valid @RequestBody ProductDTO req) {
		return productService.createProduct(req);
	}

	@PutMapping("/{id}")
	@ResponseBody
	public ProductDTO updateProduct(@PathVariable Integer id, @Valid @RequestBody ProductDTO req) {
		ProductDTO response = productService.updateProduct(id, req);
		return response;
	}

	@GetMapping("/{id}")
	@ResponseBody
	public ProductDTO getProduct(@PathVariable Integer id) {
		ProductDTO response = productService.getProduct(id);
		return response;
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public void deleteProduct(@PathVariable Integer id) {
		productService.deleteProduct(id);
	}

	@GetMapping
	public Page<ProductDTO> getProducts(@PageableDefault(size = DEFAULT_PAGE_SIZE) Pageable pageable) {
		Page<ProductDTO> response = productService.getAllProduct(pageable);
		return response;
	}

	@PostMapping("/{id}/buy")
	@ResponseBody
	public ProductDTO buyProduct(@PathVariable Integer id, @Valid @RequestBody OrderDTO order) {
		return productService.buyProduct(id, order);
	}

	@PostMapping("/{id}/stock")
	@ResponseBody
	public ProductDTO changeStock(@PathVariable Integer id, @Valid @RequestBody StockDTO stock) {
		return productService.changeStock(id, stock);
	}

}
