package com.pavan.service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.pavan.model.Product;

@Validated
public interface ProductService {

	@NotNull
	Iterable<Product> getAllProducts();

	Product getProduct(@Min(value = 1L, message = "Invalid product ID.") long id);

	Product save(Product product);
}