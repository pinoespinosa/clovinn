package com.clovinn.kardex.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.clovinn.kardex.dto.OrderDTO;
import com.clovinn.kardex.dto.ProductDTO;
import com.clovinn.kardex.dto.StockDTO;
import com.clovinn.kardex.exception.InvalidProductException;
import com.clovinn.kardex.exception.OutOfStockProductException;
import com.clovinn.kardex.model.ProductModel;
import com.clovinn.kardex.model.repository.ProductRepository;
import com.clovinn.kardex.service.IProductService;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

@Service
public class ProductServiceImpl implements IProductService {

	private final static Logger LOGGER = Logger.getLogger(ProductServiceImpl.class.getName());

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProductDTO createProduct(ProductDTO req) {
		ProductModel newProduct = modelMapper.map(req, ProductModel.class);
		ProductModel saveResult = productRepository.save(newProduct);
		ProductDTO response = modelMapper.map(saveResult, ProductDTO.class);
		return response;
	}

	@Override
	public ProductDTO updateProduct(Integer id, @Valid ProductDTO req) {

		Optional<ProductModel> optionalProd = productRepository.findById(id);
		if (!optionalProd.isPresent()) {
			LOGGER.log(Level.SEVERE, "Invalid Product ID:" + id);
			throw new InvalidProductException();
		}
		
		ProductModel prod = optionalProd.get();
		prod.setImage(req.getImage());
		prod.setName(req.getName());
		prod.setPrice(req.getPrice());
		prod.setStock(req.getStock());
		
		ProductModel saveResult = productRepository.save(prod);
		return modelMapper.map(saveResult, ProductDTO.class);
		
		
	}
	
	@Override
	public Page<ProductDTO> getAllProduct(Pageable pageable) {
		Page<ProductModel> reportList = productRepository.findAll(pageable);
		List<ProductDTO> aux = new ArrayList<ProductDTO>();
		for (ProductModel prodModel : reportList.getContent()) {
			aux.add(modelMapper.map(prodModel, ProductDTO.class));
		}
		Page<ProductDTO> response = new PageImpl<ProductDTO>(aux, pageable, reportList.getTotalElements());
		return response;
	}

	@Override
	public ProductDTO getProduct(Integer id) {
		Optional<ProductModel> optionalProd = productRepository.findById(id);
		if (!optionalProd.isPresent()) {
			LOGGER.log(Level.SEVERE, "Invalid Product ID:" + id);
			throw new InvalidProductException();
		}
		return modelMapper.map(optionalProd.get(), ProductDTO.class);
	}

	/**
	 * This method is synchronized to avoid inconsistencies in parallel calls
	 * between the stock checking and the stock removal
	 */
	@Override
	public synchronized ProductDTO buyProduct(Integer productId, OrderDTO order) {

		Optional<ProductModel> optionalProd = productRepository.findById(productId);
		if (!optionalProd.isPresent()) {
			LOGGER.log(Level.SEVERE, "Invalid Product ID:" + productId);
			throw new InvalidProductException();
		}
		ProductModel prod = optionalProd.get();

		if (prod.getStock() < order.getAmmount() || order.getAmmount() < 0) {
			LOGGER.log(Level.SEVERE, "OutOfStock ID:" + productId + ". Current Stock:" + prod.getStock() + ". Order Stock"
					+ order.getAmmount());
			throw new OutOfStockProductException();
		}

		prod.setStock(prod.getStock() - order.getAmmount());
		ProductModel saveResult = productRepository.save(prod);
		return modelMapper.map(saveResult, ProductDTO.class);
	}

	@Override
	public synchronized ProductDTO changeStock(Integer id, StockDTO stock) {

		Optional<ProductModel> optionalProd = productRepository.findById(id);
		if (!optionalProd.isPresent()) {
			LOGGER.log(Level.SEVERE, "Invalid Product ID:" + id);
			throw new InvalidProductException();
		}

		ProductModel product = optionalProd.get();
		product.setStock(stock.getAmmount());
		ProductModel saveResult = productRepository.save(product);
		return modelMapper.map(saveResult, ProductDTO.class);
	}

	@Override
	public void deleteProduct(Integer id) {
		productRepository.deleteById(id);
	}



}
