package com.appmock.services;

import java.util.List;
import java.util.Optional;

/* -> ****
 * */

import com.appmock.dao.CuestionarioDAO;
import com.appmock.dao.CuestionarioPreguntaDAO;
import com.appmock.models.Cuestionario;

public class CuestionarioServiceImp implements CuestionarioService {
	
	private CuestionarioDAO cuestionarioDao;
	private CuestionarioPreguntaDAO cuestionarioPreguntaDao;
	
	// Contructor para la dependencia
	public CuestionarioServiceImp(CuestionarioDAO cuestionarioDao, CuestionarioPreguntaDAO cuestionarioPreguntaDao) {
		this.cuestionarioDao = cuestionarioDao;
		this.cuestionarioPreguntaDao = cuestionarioPreguntaDao;
	}

//	public Cuestionario findCuestionarioPorNombre(String nombre) {
//		Optional<Cuestionario> cuestionarioOptional = cuestionarioDao.findAll()
//			.stream()
//			.filter(e -> e.getNombre().contains(nombre))
//			.findFirst();
//		Cuestionario examen = null;
//		if(cuestionarioOptional.isPresent()) {
//			examen = cuestionarioOptional.orElseThrow();
//		}
//		return examen;
//	}
	public Optional<Cuestionario> findCuestionarioPorNombre(String nombre) {
		return cuestionarioDao.findAll()
			.stream()
			.filter(e -> e.getNombre().contains(nombre))
			.findFirst();
		
	}

	@Override
	public Cuestionario findCuestionarioPorNombreConPreguntas(String nombre) {
		Optional<Cuestionario> cuestionarioOp = findCuestionarioPorNombre(nombre);
		Cuestionario cuestionario = null;
		if(cuestionarioOp.isPresent()) {
			cuestionario = cuestionarioOp.orElseThrow();
			List<String> preguntas = cuestionarioPreguntaDao.findPreguntasPorCuestionarioId(cuestionario.getId());
			cuestionario.setPreguntas(preguntas);
		}
		return cuestionario;
	}

	@Override
	public Cuestionario guardar(Cuestionario examen) {
		if(!examen.getPreguntas().isEmpty()) {
			cuestionarioPreguntaDao.guardarVarias(examen.getPreguntas());
		}
		return cuestionarioDao.guardar(examen);
	}

}
