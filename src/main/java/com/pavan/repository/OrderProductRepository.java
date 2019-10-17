package com.pavan.repository;

import org.springframework.data.repository.CrudRepository;

import com.pavan.model.OrderProduct;
import com.pavan.model.OrderProductPK;

public interface OrderProductRepository extends CrudRepository<OrderProduct, OrderProductPK> {
}
