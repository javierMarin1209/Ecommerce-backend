package com.ias.Ecommerce.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.ias.Ecommerce.models.entity.Order;

public interface OrderDAO extends CrudRepository<Order, Integer>,QueryByExampleExecutor<Order>{

	

}
