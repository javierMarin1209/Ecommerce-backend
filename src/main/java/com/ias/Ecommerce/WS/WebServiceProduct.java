package com.ias.Ecommerce.WS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ias.Ecommerce.models.service.ProductService;
import com.ias.Ecommerce.object.RequestProduct;
import com.ias.Ecommerce.object.Response;

@RestController
@RequestMapping("/Ecommerce/product")
public class WebServiceProduct {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/create")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Response Create(@RequestBody RequestProduct requestProduct) {
		return productService.Create(requestProduct);
	}
	
	@PostMapping("/findAll")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Response FindAll() {
		return productService.FindAll();
	}
	
	@PostMapping("/update")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Response Update(@RequestBody RequestProduct requestProduct) {
		return productService.Update(requestProduct);
	}
	
	@PostMapping("/delete")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Response Delete(@RequestBody RequestProduct requestProduct) {
		return productService.Delete(requestProduct);
	}
	
	@PostMapping("/findByName")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Response FindByName(@RequestBody RequestProduct requestProduct) {
		return productService.FindByName(requestProduct);
	}
}
