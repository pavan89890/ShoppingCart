package com.pavan.modal;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class OrderProductPK implements Serializable {

	@JsonBackReference
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	// standard getters and setters

	// hashcode() and equals() methods
}
