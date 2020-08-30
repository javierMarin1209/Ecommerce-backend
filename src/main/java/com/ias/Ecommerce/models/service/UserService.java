package com.ias.Ecommerce.models.service;

import com.ias.Ecommerce.models.entity.User;
import com.ias.Ecommerce.object.Response;


public interface UserService {
	
	public Response Create(User user);
	
	public Response BlockUser(com.ias.Ecommerce.object.BlockUser blockUser);
	
	public Response LogIn(User user);
	
	public Response ChangePassword(User user);
	
	public Response forgotPassword(User user);
	
	public Response ConfirmNewpassword(User user);

}
