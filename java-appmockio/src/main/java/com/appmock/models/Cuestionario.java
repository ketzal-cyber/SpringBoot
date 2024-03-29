package com.appmock.models;

import java.util.ArrayList;
import java.util.List;

/* -> *
 * */

public class Cuestionario {
	
	private Long id;
	private String nombre;
	private List<String> preguntas;
	
	public Cuestionario(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.preguntas = new ArrayList<String>();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<String> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(List<String> preguntas) {
		this.preguntas = preguntas;
	}
	
	

}
