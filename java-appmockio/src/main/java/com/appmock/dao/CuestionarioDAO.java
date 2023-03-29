package com.appmock.dao;

import java.util.List;

/* 	-> ***
 * Metodo que sera simulado con Mock
 */

import com.appmock.models.Cuestionario;

public interface CuestionarioDAO {
	
	List<Cuestionario> findAll();
	
	Cuestionario guardar(Cuestionario cuestionario);

}
