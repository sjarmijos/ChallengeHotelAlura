package com.alurahotel.modelo;

import java.sql.Date;

public class Reserva {

	private Integer id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private Double valor;
	private String formaPago;
	private String tipoHabitacion;

	public Reserva(Date fechaEntrada, Date fechaSalida, double valor, String formaPago, String tipoHabitacion) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
		this.tipoHabitacion = tipoHabitacion;
	}

	public Reserva(int id, Date fechaEntrada, Date fechaSalida, double valor, String formaPago, String tipoHabitacion) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
		this.tipoHabitacion = tipoHabitacion;
	}

	public Date getFechaEntrada() {
		return this.fechaEntrada;
	}
	
	public Date getFechaSalida() {
		return this.fechaSalida;
	}
	
	public Double getValor() {
		return valor;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getTipoHabitacion() {
		return this.tipoHabitacion;
	}


	@Override
	public String toString() {
		return "{Id: " + this.id + ", Fecha de entrada: " + this.fechaEntrada + ", Fecha de Salida: "
				+ this.fechaSalida+", Valor: "+this.valor+"}";
	}

	


}
