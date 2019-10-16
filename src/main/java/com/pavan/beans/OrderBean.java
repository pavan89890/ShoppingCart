package com.pavan.beans;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderBean {
	private Long id;
	private List<OrderItemsBean> orderItems;
}
