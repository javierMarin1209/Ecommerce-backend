package com.ias.Ecommerce.models.service;

import com.ias.Ecommerce.models.entity.User;
import com.ias.Ecommerce.object.Response;

/**
 * Esta clase es la interface para la logica de negocio de eliminar un usuario
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public interface UserService {
	
	public Response Create(User user);
	
	public Response BlockUser(com.ias.Ecommerce.object.BlockUser blockUser);
	
	public Response LogIn(User user);
	
	public Response ChangePassword(User user);
	
	public Response forgotPassword(User user);
	
	public Response ConfirmNewpassword(User user);

}
