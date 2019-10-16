package com.pavan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pavan.modal.Item;

@Repository
public interface ItemRespository extends JpaRepository<Item, Long> {

}
