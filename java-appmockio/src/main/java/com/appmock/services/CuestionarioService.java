package com.appmock.services;

import java.util.Optional;

import com.appmock.models.Cuestionario;

/* -> **
 * */

public interface CuestionarioService {
	
//	Cuestionario findCuestionarioPorNombre(String nombre);
	Optional<Cuestionario> findCuestionarioPorNombre(String nombre);
	
	Cuestionario findCuestionarioPorNombreConPreguntas(String nombre);
	
	Cuestionario guardar(Cuestionario examen);

}
