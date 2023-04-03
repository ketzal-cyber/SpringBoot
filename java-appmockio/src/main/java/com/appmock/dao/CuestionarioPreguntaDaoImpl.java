package com.appmock.dao;

import java.util.Arrays;
import java.util.List;

import com.appmock.models.Cuestionario;

public class CuestionarioPreguntaDaoImpl implements CuestionarioPreguntaDAO {
	
	public static final List<String> PREGUNTAS = Arrays.asList("JAVA", "Android", "C",
			"Python", "Logica");

	@Override
	public List<String> findPreguntasPorCuestionarioId(Long id) {
		System.out.println("Ejecion de metodo - findPreguntasPorCuestionarioId");
		return PREGUNTAS;
	}

	@Override
	public void guardarVarias(List<String> preguntas) {
		System.out.println("Ejecion de metodo - guardarVarias");
		
	}

	

}
