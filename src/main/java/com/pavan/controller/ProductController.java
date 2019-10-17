package com.pavan.controller;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.model.Product;
import com.pavan.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping(value = { "/" })
	public @NotNull Iterable<Product> getProducts() {
		return productService.getAllProducts();
	}
}