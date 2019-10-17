package com.pavan.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pavan.model.OrderProduct;
import com.pavan.repository.OrderProductRepository;
import com.pavan.service.OrderProductService;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService {

    private OrderProductRepository orderProductRepository;

    public OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public OrderProduct create(OrderProduct orderProduct) {
        return this.orderProductRepository.save(orderProduct);
    }
}