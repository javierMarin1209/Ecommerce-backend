package com.ias.Ecommerce.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.ias.Ecommerce.models.entity.Order;
import com.ias.Ecommerce.object.OrderStatus;

public interface OrderDAO extends CrudRepository<Order, Integer>,QueryByExampleExecutor<Order>{

	@Query("SELECT o FROM Order o WHERE (o.state) = (?1)")
	List<Order> FindBystatus(OrderStatus id);
	
	@Query("Select COUNT(o) FROM Order o WHERE (o.state) = (?1)")
	Integer CountAllByStatus(OrderStatus id);

}
