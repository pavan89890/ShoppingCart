package com.pavan.service;

import org.springframework.stereotype.Service;

import com.pavan.beans.ApiResponse;
import com.pavan.beans.ItemBean;

@Service
public interface ItemService {

	public void saveItem(ItemBean itemBean) throws Exception;

	public ApiResponse getItems();

	public ApiResponse getItem(Long id);

	public ApiResponse deleteItem(Long id);

	public ApiResponse deleteItems();

}
