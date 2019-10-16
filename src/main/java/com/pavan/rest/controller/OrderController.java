package com.pavan.rest.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.beans.ApiResponse;
import com.pavan.beans.OrderBean;
import com.pavan.service.OrderService;
import com.pavan.util.Utility;

@RestController
@RequestMapping(path = "/api/orders")
@CrossOrigin("*")
public class OrderController {

	@Autowired
	private OrderService orderService;

	private String message;

	@PostMapping
	@Transactional(rollbackOn = Exception.class)
	public ApiResponse saveOrder(@RequestBody(required = true) OrderBean orderBean) {

		try {
			orderService.saveOrder(orderBean);

			if (Utility.isEmpty(orderBean.getId())) {
				message = "Order saved successfully";
			} else {
				message = "Order updated successfully";
			}

			return new ApiResponse(HttpStatus.OK, message, null);
		} catch (Exception e) {
			message = "Error-" + e.getMessage();
			return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, null);
		}

	}

	@GetMapping
	public ApiResponse orders() {
		return orderService.getOrders();
	}

	@GetMapping("/{id}")
	public ApiResponse getOrder(@PathVariable(value = "id") Long id) {
		return orderService.getOrder(id);
	}

	@DeleteMapping("/{id}")
	public ApiResponse deleteOrder(@PathVariable(value = "id") Long id) {
		return orderService.deleteOrder(id);
	}

	@DeleteMapping
	public ApiResponse deleteOrders() {
		return orderService.deleteOrders();
	}
}
