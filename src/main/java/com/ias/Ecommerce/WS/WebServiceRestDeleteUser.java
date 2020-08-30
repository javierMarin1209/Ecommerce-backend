package com.ias.Ecommerce.WS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ias.Ecommerce.models.service.FacadeDeleteUser;
import com.ias.Ecommerce.object.Response;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
public class WebServiceRestDeleteUser {

	@Autowired
	private FacadeDeleteUser delete;
	@PostMapping("/deleteUser")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Response DeleteUser(@RequestHeader("Email") String email,@RequestBody String correo) {
		return delete.Delete(correo, email);
	}

}
