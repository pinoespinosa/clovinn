package com.clovinn.kardex.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.clovinn.kardex.model.ProductModel;



@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductModel, Integer> {


	
}
