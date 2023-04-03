package com.appmock.services;

import java.util.Arrays;
import java.util.List;

import com.appmock.models.Cuestionario;

public class Datos {
	
	public static final List<Cuestionario> EXAMENES = Arrays.asList(new Cuestionario(5L,"Programación"),
			new Cuestionario(6L, "Analisis Sistemas"),
			new Cuestionario(7L, "Matematicas Discretas"));
	
	public static final List<Cuestionario> EXAMENES_ID_NULL = Arrays.asList(new Cuestionario(null,"Programación"),
			new Cuestionario(null, "Analisis Sistemas"),
			new Cuestionario(null, "Matematicas Discretas"));
	
	public static final List<String> PREGUNTAS = Arrays.asList("JAVA", "Android", "C",
												"Python", "Logica");
//	public static final Cuestionario cuestionario = new Cuestionario(8L, "ForenseTI");
	public static final Cuestionario cuestionario = new Cuestionario(null, "ForenseTI");
	
	
	public static final List<Cuestionario> EXAMENES_ID_NEGATIVOS = Arrays.asList(new Cuestionario(-5L,"Programación"),
			new Cuestionario(-6L, "Analisis Sistemas"),
			new Cuestionario(null, "Matematicas Discretas"));

}
