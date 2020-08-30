package com.ias.Ecommerce.WS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ias.Ecommerce.models.entity.User;
import com.ias.Ecommerce.models.service.UserService;
import com.ias.Ecommerce.object.Response;


@RestController
@RequestMapping("/Ecommerce/user")
public class WebServiceUser {

	@Autowired
	private UserService userService;
	
	
	@PostMapping("/create")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Response CreateUser(@RequestBody User user) {
		return userService.Create(user);
	}
	
	@PostMapping("/block")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Response BlockUser(@RequestBody com.ias.Ecommerce.object.BlockUser user) {
		return userService.BlockUser(user);
	}
	
	@PostMapping("/logIn")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Response LogIn(@RequestBody User user) {
		return userService.LogIn(user);
	}
	
	@PostMapping("/changePassword")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Response ChangePassword(@RequestBody User user) {
		return userService.ChangePassword(user);
	}
	
	
	@PostMapping("/forgotPassword")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Response forgotPassword(@RequestBody User user) {
		return userService.forgotPassword(user);
	}
	
	@PostMapping("/confirmPassword")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Response ConfirmPassword(@RequestBody User user) {
		return userService.ConfirmNewpassword(user);
	}
	

}
