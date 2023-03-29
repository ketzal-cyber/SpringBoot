package com.appmock.dao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.appmock.models.Cuestionario;

/* -> *****
 * clase de simulacion de acceso a datos
 * */

public class CuestionarioDaoImpl implements CuestionarioDAO {

	@Override
	public List<Cuestionario> findAll() {
		return Collections.emptyList();
		
//		return Arrays.asList(new Cuestionario(5L,"Programación"),
//							new Cuestionario(6L, "Analisis Sistemas"),
//							new Cuestionario(7L, "Matematicas Discretas"));
	}

	@Override
	public Cuestionario guardar(Cuestionario cuestionario) {
		// TODO Auto-generated method stub
		return null;
	}

}
