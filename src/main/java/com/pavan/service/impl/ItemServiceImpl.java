package com.pavan.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pavan.beans.ApiResponse;
import com.pavan.beans.ItemBean;
import com.pavan.modal.Item;
import com.pavan.repository.ItemRespository;
import com.pavan.service.ItemService;
import com.pavan.util.Utility;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemRespository itemRepository;

	private String message = "";

	@Override
	public ApiResponse getItems() {

		List<Item> items = itemRepository.findAll();

		if (Utility.isEmpty(items)) {
			return new ApiResponse(HttpStatus.NO_CONTENT, "No data found", null);
		}

		return new ApiResponse(HttpStatus.OK, null, items);
	}

	@Override
	public void saveItem(ItemBean itemBean) throws Exception {

		if (!validData(itemBean)) {
			throw new Exception(message);
		}

		Item item = new Item();
		if (itemBean.getId() != null) {
			item.setId(itemBean.getId());
		}

		item.setName(itemBean.getName());
		item.setPrice(itemBean.getPrice());

		itemRepository.save(item);

	}

	private boolean validData(ItemBean bean) {

		if (Utility.isEmpty(bean.getName())) {
			message = "Please Enter Item Name";
			return false;
		}

		if (Utility.isEmpty(bean.getPrice())) {
			message = "Please Enter Item Price";
			return false;
		}

		return true;
	}

	@Override
	public ApiResponse getItem(Long id) {
		if (id == null || id == 0) {
			return new ApiResponse(HttpStatus.NO_CONTENT, "No data found", null);
		} else {

			Optional<Item> itemOp = itemRepository.findById(id);
			if (itemOp.isPresent()) {
				Item item = itemOp.get();
				return new ApiResponse(HttpStatus.OK, null, item);
			} else {
				return new ApiResponse(HttpStatus.NO_CONTENT, "No data found", null);
			}
		}
	}

	@Override
	public ApiResponse deleteItem(Long id) {
		if (getItem(id).getData() == null) {
			return new ApiResponse(HttpStatus.NO_CONTENT, "No data found", null);
		} else {
			itemRepository.delete((Item) getItem(id).getData());
			message = "Item deleted successfully";
			return new ApiResponse(HttpStatus.OK, message, null);
		}
	}

	@Override
	public ApiResponse deleteItems() {
		itemRepository.deleteAll();
		message = "Items deleted successfully";
		return new ApiResponse(HttpStatus.OK, message, null);
	}

}
