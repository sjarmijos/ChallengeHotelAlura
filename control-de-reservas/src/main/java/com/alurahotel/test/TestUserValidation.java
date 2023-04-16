package com.alurahotel.test;

import com.alurahotel.controller.UserController;
import com.alurahotel.modelo.User;

public class TestUserValidation {

	public static void main(String[] args) {
		User user = new User();
		UserController userController = new UserController();
		
		user.setNombreUsuario("admin");
		user.setPassword("admin");
		
		if(userController.validarUsuario(user.getNombreUsuario(), user.getPassword())) {
			System.out.println("Usuario Correcto, Accediendo...");
		}else {
			System.out.println("Usuario o Contraseña incorrecto, intentelo de nuevo");
		}
	}


}
