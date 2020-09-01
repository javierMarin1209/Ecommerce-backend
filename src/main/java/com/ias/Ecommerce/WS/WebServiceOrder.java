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
	
	@PostMapping("/findAll")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Response FindAll() {
		return orderService.FindAll();
	}
	
	@PostMapping("/delete")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Response Delete(@RequestBody RequestOrder order)  {
		return orderService.Delete(order);
	}
	
	@PostMapping("/findByUser")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Response findByUser(@RequestBody RequestOrder order)  {
		return orderService.FindByUser(order);
	}
	
	@PostMapping("/findOne")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Response findOne(@RequestBody RequestOrder order)  {
		return orderService.FindOne(order);
	}
	
	@PostMapping("/payOrder")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Response payOrder(@RequestBody RequestOrder order)  {
		return orderService.PayOrder(order);
	}
	
	@PostMapping("/delivery")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Response delivery(@RequestBody RequestOrder order)  {
		return orderService.DeliveryOrder(order);
	}
	
	@PostMapping("/addProduct")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Response AddProduct(@RequestBody RequestOrder order)  {
		return orderService.AddProduct(order);
	}
	
	@PostMapping("/deleteProduct")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Response DeleteProduct(@RequestBody RequestOrder order)  {
		return orderService.DeleteProduct(order);
	}
	
	@PostMapping("/findAllByStatus")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Response FindAllByStatus(@RequestBody Order status)  {
		return orderService.FindAllByStatus(status);
	}
	
	@PostMapping("/countAllByStatus")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Response CountAllByStatus(@RequestBody Order status)  {
		return orderService.CountAllByStatus(status);
	}
}
