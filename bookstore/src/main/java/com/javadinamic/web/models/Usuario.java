package com.javadinamic.web.models;

import java.util.Date;

public class Usuario extends Persona {

	private int codUser;
	private String usuario;
	private String passord;
	private String logros;
	private Date fechaIngreso;
	private Date fechasalida;
	
	//Constructorres
	public Usuario() {
		
	}
	
	public Usuario(int codUser, String usuario, String passord, String logros, Date fechaIngreso, Date fechasalida) {
		this.codUser = codUser;
		this.usuario = usuario;
		this.passord = passord;
		this.logros = logros;
		this.fechaIngreso = fechaIngreso;
		this.fechasalida = fechasalida;
	}
	
	public Usuario(int codUser, String usuario, String logros, Date fechaIngreso) {
		this.codUser = codUser;
		this.usuario = usuario;
		this.logros = logros;
		this.fechaIngreso = fechaIngreso;
	}

	public Usuario(String nombbre, String apeP, String apeM, String dNI, char genero, String domicilio,
			String telefono, String email, Date fechaNacimiento, String usuario,String logros,Date fechaIngreso) {
		super(nombbre,apeP,apeM,dNI,genero,domicilio,telefono,email,fechaNacimiento);
		this.usuario = usuario;
		this.logros = logros;
		this.fechaIngreso = fechaIngreso;
	}



	//Metodos getters and setters
	public int getCodUser() {
		return codUser;
	}
	public void setCodUser(int codUser) {
		this.codUser = codUser;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassord() {
		return passord;
	}
	public void setPassord(String passord) {
		this.passord = passord;
	}
	public String getLogros() {
		return logros;
	}
	public void setLogros(String logros) {
		this.logros = logros;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public Date getFechasalida() {
		return fechasalida;
	}
	public void setFechasalida(Date fechasalida) {
		this.fechasalida = fechasalida;
	}
	@Override
	public String toString() {
		return "Usuario [codUser=" + codUser + ", usuario=" + usuario + ", passord=" + passord + ", logros=" + logros
				+ ", fechaIngreso=" + fechaIngreso + ", fechasalida=" + fechasalida + "]";
	}
	
	
	
	
	
}
