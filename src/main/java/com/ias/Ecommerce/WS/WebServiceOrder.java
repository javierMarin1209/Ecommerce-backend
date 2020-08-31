package com.ias.Ecommerce.WS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ias.Ecommerce.models.entity.Order;
import com.ias.Ecommerce.models.service.OrderService;
import com.ias.Ecommerce.object.RequestOrder;
import com.ias.Ecommerce.object.Response;

@RestController
@RequestMapping("/Ecommerce/order")
public class WebServiceOrder {
	

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/create")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Response Create(@RequestBody RequestOrder order) {
		return orderService.Create(order);
	}
}
