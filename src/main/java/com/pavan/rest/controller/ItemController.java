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
import com.pavan.beans.ItemBean;
import com.pavan.service.ItemService;
import com.pavan.util.Utility;

@RestController
@RequestMapping(path = "/api/items")
@CrossOrigin("*")
public class ItemController {

	@Autowired
	private ItemService itemService;

	private String message;

	@PostMapping
	@Transactional(rollbackOn = Exception.class)
	public ApiResponse saveItem(@RequestBody(required = true) ItemBean itemBean) {

		try {
			itemService.saveItem(itemBean);

			if (Utility.isEmpty(itemBean.getId())) {
				message = "Item saved successfully";
			} else {
				message = "Item updated successfully";
			}

			return new ApiResponse(HttpStatus.OK, message, null);
		} catch (Exception e) {
			message = "Error-" + e.getMessage();
			return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, null);
		}

	}

	@GetMapping
	public ApiResponse items() {
		return itemService.getItems();
	}

	@GetMapping("/{id}")
	public ApiResponse getItem(@PathVariable(value = "id") Long id) {
		return itemService.getItem(id);
	}

	@DeleteMapping("/{id}")
	public ApiResponse deleteItem(@PathVariable(value = "id") Long id) {
		return itemService.deleteItem(id);
	}

	@DeleteMapping
	public ApiResponse deleteItems() {
		return itemService.deleteItems();
	}
}
