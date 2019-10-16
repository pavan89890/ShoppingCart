package com.pavan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pavan.modal.Order;

@Repository
public interface OrderRespository extends JpaRepository<Order, Long> {

}
