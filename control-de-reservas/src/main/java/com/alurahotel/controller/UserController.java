package com.alurahotel.controller;

import com.alurahotel.dao.UserDAO;
import com.alurahotel.factory.ConnectionFactory;

public class UserController {
	
	private UserDAO userDAO;
	
	public UserController() {
		this.userDAO = new UserDAO(new ConnectionFactory().conectar());
	}
	
	public boolean validarUsuario(String nombreUsuario, String password) {
		return userDAO.existeUsuario(nombreUsuario, password);
	}

}
