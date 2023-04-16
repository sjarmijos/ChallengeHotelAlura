package com.alurahotel.modelo;

public class User {

	private String nombreUsuario;
	private String password;

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "{Nombre_usuario: " + this.nombreUsuario + ", Contraseña: " + this.password + " }";
	}

}
