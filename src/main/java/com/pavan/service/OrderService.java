package com.pavan.service;

import org.springframework.stereotype.Service;

import com.pavan.beans.ApiResponse;
import com.pavan.beans.OrderBean;

@Service
public interface OrderService {

	public void saveOrder(OrderBean orderBean) throws Exception;

	public ApiResponse getOrders();

	public ApiResponse getOrder(Long id);

	public ApiResponse deleteOrder(Long id);

	public ApiResponse deleteOrders();

}
