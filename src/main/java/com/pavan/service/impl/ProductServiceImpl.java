package com.pavan.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pavan.exception.ResourceNotFoundException;
import com.pavan.model.Product;
import com.pavan.repository.ProductRepository;
import com.pavan.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Iterable<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProduct(long id) {
		return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}
}