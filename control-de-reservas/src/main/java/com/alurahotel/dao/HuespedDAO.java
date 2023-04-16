package com.alurahotel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alurahotel.modelo.Huesped;

public class HuespedDAO {

	private Connection con;

	public HuespedDAO(Connection con) {
		this.con = con;
	}

	// Method to Create a new temporaly resident

	public void crear(Huesped huesped) {
		try {
			con.setAutoCommit(false);

			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO huesped (nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva) VALUES(?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				guardarRegistro(huesped, statement);
				con.commit();
			} catch (Exception e) {
				e.getMessage();
				con.rollback();
			}
		} catch (SQLException e) {
			e.getMessage();
			throw new RuntimeException(e);
		}
	}

	private void guardarRegistro(Huesped huesped, PreparedStatement statement) throws SQLException {
		statement.setString(1, huesped.getNombre());
		statement.setString(2, huesped.getApellido());
		statement.setDate(3, huesped.getFechaNacimiento());
		statement.setString(4, huesped.getNacionalidad());
		statement.setString(5, huesped.getTelefono());
		statement.setInt(6, huesped.getReservaId());

		statement.execute();

		final ResultSet resultSet = statement.getGeneratedKeys();
		try (resultSet) {
			while (resultSet.next()) {
				huesped.setId(resultSet.getInt(1));
				System.out.println("Fue insertado el producto de id: " + huesped.toString());
			}
		}

	}

	//Method to find current reservation
	public Integer idReservaActual() {
		int id = 0;
		try {
			var querySelect = "SELECT ID FROM reserva ORDER BY id DESC LIMIT 1;";
			final PreparedStatement statement = con.prepareStatement(querySelect);
			try (statement) {
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();
				while (resultSet.next()) {
					id = resultSet.getInt("ID");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return id;
	}
	
	//Method to get all Residents

	public List<Huesped> listar() {
		List<Huesped> huespedes = new ArrayList<>();

		try {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT ID, NOMBRE, APELLIDO, FECHANACIMIENTO, NACIONALIDAD, TELEFONO, IDRESERVA FROM huesped;");
			try (statement) {
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();
				while (resultSet.next()) {
					Huesped fila = new Huesped(resultSet.getInt("ID"), resultSet.getString("NOMBRE"),
							resultSet.getString("APELLIDO"), resultSet.getDate("FECHANACIMIENTO"),
							resultSet.getString("Nacionalidad"), resultSet.getString("TELEFONO"),
							resultSet.getInt("IDRESERVA"));
					huespedes.add(fila);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return huespedes;
	}
	
	//Method to modify a Resident

	public int modificar(Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
			String telefono, int reservaId) {
		
		try {
			final PreparedStatement statement = con.prepareStatement(
					"UPDATE huesped SET " + "nombre = ?, " + "apellido = ?, " + "fechaNacimiento = ?, "
							+ "nacionalidad = ?, " + "telefono = ?, " + "idReserva = ? WHERE id = ? ;");

			try (statement) {
				statement.setString(1, nombre);
				statement.setString(2, apellido);
				statement.setDate(3, fechaNacimiento);
				statement.setString(4, nacionalidad);
				statement.setString(5, telefono);
				statement.setInt(6, reservaId);
				statement.setInt(7, id);

				statement.execute();

				int updateCount = statement.getUpdateCount();

				return updateCount;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Method to delete a resident
	
	public int eliminar(Integer id) {
		
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM huesped WHERE ID = ? ;");
			try(statement){
				statement.setInt(1, id);
				statement.execute();
				return statement.getUpdateCount();
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Method to get resident identified by his ID
	
	public List<Huesped> listar(Integer id) {
		List<Huesped> huesped = new ArrayList<>();

		try {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT ID, NOMBRE, APELLIDO, FECHANACIMIENTO, NACIONALIDAD, TELEFONO, IDRESERVA FROM huesped WHERE IDRESERVA = ?");
			try (statement) {
				statement.setInt(1, id);
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();
				while (resultSet.next()) {
					Huesped fila = new Huesped(resultSet.getInt("ID"), resultSet.getString("NOMBRE"),
							resultSet.getString("APELLIDO"), resultSet.getDate("FECHANACIMIENTO"),
							resultSet.getString("Nacionalidad"), resultSet.getString("TELEFONO"),
							resultSet.getInt("IDRESERVA"));
					huesped.add(fila);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return huesped;
	}
	
	//Method to get resident identified by his last name

	public List<Huesped> listar(String apellido) {
		List<Huesped> huesped = new ArrayList<>();

		try {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT ID, NOMBRE, APELLIDO, FECHANACIMIENTO, NACIONALIDAD, TELEFONO, IDRESERVA FROM huesped WHERE APELLIDO = ?");
			try (statement) {
				statement.setString(1, apellido);
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();
				while (resultSet.next()) {
					Huesped fila = new Huesped(resultSet.getInt("ID"), resultSet.getString("NOMBRE"),
							resultSet.getString("APELLIDO"), resultSet.getDate("FECHANACIMIENTO"),
							resultSet.getString("Nacionalidad"), resultSet.getString("TELEFONO"),
							resultSet.getInt("IDRESERVA"));
					huesped.add(fila);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return huesped;
	}

}






