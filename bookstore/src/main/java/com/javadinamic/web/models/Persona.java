package com.javadinamic.web.models;

import java.util.Date;

public class Persona {
	
	//Variales de Instancia
	private int codPersona;
	private String nombbre;
	private String apeP;
	private String apeM;
	private String DNI;
	private char genero;
	private String domicilio;
	private String telefono;
	private String email;
	private Date fechaNacimiento;
	
	//constructores
	public Persona() {
		
	}
	
	public Persona(int codPersona, String nombbre, String apeP, String apeM, String dNI, char genero, String domicilio,
			String telefono, String email, Date fechaNacimiento) {
		this.codPersona = codPersona;
		this.nombbre = nombbre;
		this.apeP = apeP;
		this.apeM = apeM;
		DNI = dNI;
		this.genero = genero;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
	}
	


	public Persona(String nombbre, String apeP, String apeM, String dNI, char genero, String domicilio, String telefono,
			String email, Date fechaNacimiento) {
		this.nombbre = nombbre;
		this.apeP = apeP;
		this.apeM = apeM;
		DNI = dNI;
		this.genero = genero;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
	}

	public Persona(int codPersona) {
		super();
		this.codPersona = codPersona;
	}


	//Metodos getter and settters
	public int getCodPersona() {
		return codPersona;
	}
	public void setCodPersona(int codPersona) {
		this.codPersona = codPersona;
	}
	public String getNombbre() {
		return nombbre;
	}
	public void setNombbre(String nombbre) {
		this.nombbre = nombbre;
	}
	public String getApeP() {
		return apeP;
	}
	public void setApeP(String apeP) {
		this.apeP = apeP;
	}
	public String getApeM() {
		return apeM;
	}
	public void setApeM(String apeM) {
		this.apeM = apeM;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public char getGenero() {
		return genero;
	}
	public void setGenero(char genero) {
		this.genero = genero;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	@Override
	public String toString() {
		return "Persona [codPersona=" + codPersona + ", nombbre=" + nombbre + ", apeP=" + apeP + ", apeM=" + apeM + ", DNI="
				+ DNI + ", genero=" + genero + ", domicilio=" + domicilio + ", telefono=" + telefono + ", email="
				+ email + ", fechaNacimiento=" + fechaNacimiento + "]";
	}
	
	

}
