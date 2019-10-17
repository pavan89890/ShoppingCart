package com.pavan.repository;

import org.springframework.data.repository.CrudRepository;

import com.pavan.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
