package com.appmock.services;

import com.appmock.models.Cuestionario;

/* -> **
 * */

public interface CuestionarioService {
	
	Cuestionario findCuestionarioPorNombre(String nombre);

}
