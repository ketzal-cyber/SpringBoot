package com.appmock.services;

import java.util.Optional;

/* -> ****
 * */

import com.appmock.dao.CuestionarioDAO;
import com.appmock.models.Cuestionario;

public class CuestionarioServiceImp implements CuestionarioService {
	
	private CuestionarioDAO cuestionarioDao;
	// Contructor para la dependencia
	public CuestionarioServiceImp(CuestionarioDAO cuestionarioDao) {
		this.cuestionarioDao = cuestionarioDao;
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

}
