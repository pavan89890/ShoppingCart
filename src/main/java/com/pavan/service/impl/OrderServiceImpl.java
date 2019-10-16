package com.pavan.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pavan.beans.ApiResponse;
import com.pavan.beans.OrderBean;
import com.pavan.beans.OrderItemsBean;
import com.pavan.modal.Item;
import com.pavan.modal.Order;
import com.pavan.repository.ItemRespository;
import com.pavan.repository.OrderRespository;
import com.pavan.service.OrderService;
import com.pavan.util.Utility;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRespository orderRepository;

	@Autowired
	ItemRespository itemRepository;

	private String message = "";

	@Override
	public ApiResponse getOrders() {

		List<Order> orders = orderRepository.findAll();

		if (Utility.isEmpty(orders)) {
			return new ApiResponse(HttpStatus.NO_CONTENT, "No data found", null);
		}

		return new ApiResponse(HttpStatus.OK, null, orders);
	}

	@Override
	public void saveOrder(OrderBean orderBean) throws Exception {

		if (!validData(orderBean)) {
			throw new Exception(message);
		}

		Order order = new Order();
		if (orderBean.getId() != null) {
			order.setId(orderBean.getId());
		}

		List<Item> items = new ArrayList<>();

		for (OrderItemsBean orderItem : orderBean.getOrderItems()) {
			Optional<Item> itemOpt = itemRepository.findById(orderItem.getItemId());
			if (itemOpt.isPresent()) {
				Item item = itemRepository.getOne(orderItem.getItemId());
				items.add(item);
			} else {
				message = "Item with id " + orderItem.getItemId() + " doesn't exist in the database";
				throw new Exception(message);
			}
		}

		order.setItems(items);

		orderRepository.save(order);

	}

	private boolean validData(OrderBean bean) {

		if (Utility.isEmpty(bean.getOrderItems())) {
			message = "Please Select Items";
			return false;
		}

		for (OrderItemsBean item : bean.getOrderItems()) {
			if (Utility.isEmpty(item.getItemId())) {
				message = "Please Provide Item Id";
				return false;
			}
			if (Utility.isEmpty(item.getQuantity())) {
				message = "Please Provide Item Quantity";
				return false;
			}
		}

		return true;
	}

	@Override
	public ApiResponse getOrder(Long id) {
		if (id == null || id == 0) {
			return new ApiResponse(HttpStatus.NO_CONTENT, "No data found", null);
		} else {

			Optional<Order> orderOp = orderRepository.findById(id);
			if (orderOp.isPresent()) {
				Order order = orderOp.get();
				return new ApiResponse(HttpStatus.OK, null, order);
			} else {
				return new ApiResponse(HttpStatus.NO_CONTENT, "No data found", null);
			}
		}
	}

	@Override
	public ApiResponse deleteOrder(Long id) {
		if (getOrder(id).getData() == null) {
			return new ApiResponse(HttpStatus.NO_CONTENT, "No data found", null);
		} else {
			orderRepository.delete((Order) getOrder(id).getData());
			message = "Order deleted successfully";
			return new ApiResponse(HttpStatus.OK, message, null);
		}
	}

	@Override
	public ApiResponse deleteOrders() {
		orderRepository.deleteAll();
		message = "Orders deleted successfully";
		return new ApiResponse(HttpStatus.OK, message, null);
	}

}
