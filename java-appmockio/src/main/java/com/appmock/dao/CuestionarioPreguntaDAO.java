package com.appmock.dao;

import java.util.List;

/* -> ******-*
 *  clase para buscar por preguntas
 * */
public interface CuestionarioPreguntaDAO {

	List<String> findPreguntasPorCuestionarioId(Long id);
	
	void guardarVarias(List<String> preguntas);
}
