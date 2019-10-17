package com.pavan.repository;

import org.springframework.data.repository.CrudRepository;

import com.pavan.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
