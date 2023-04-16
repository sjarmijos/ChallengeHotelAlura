package com.alurahotel.test;

import java.sql.Connection;
import java.sql.SQLException;

import com.alurahotel.factory.ConnectionFactory;

public class TestConexion {
	
	public static void main(String[] args) throws SQLException{
		Connection con = new ConnectionFactory().conectar();
		
		System.out.println("Cerrando conexion");
		
		con.close();
	}

}
