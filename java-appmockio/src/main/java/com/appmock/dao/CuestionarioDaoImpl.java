package com.appmock.dao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.appmock.models.Cuestionario;

/* -> *****
 * clase de simulacion de acceso a datos
 * */

public class CuestionarioDaoImpl implements CuestionarioDAO {
	
	public static final List<Cuestionario> EXAMENES = Arrays.asList(new Cuestionario(5L,"Programación"),
			new Cuestionario(6L, "Analisis Sistemas"),
			new Cuestionario(7L, "Matematicas Discretas"));
	public static final Cuestionario cuestionario = new Cuestionario(8L, "ForenseTI");

	@Override
	public List<Cuestionario> findAll() {
		System.out.println("Ejecion de metodo - findAll");
		try {
			System.out.println("Cuestionario DAO Implement");
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return EXAMENES; //  Collections.emptyList()
		
//		return Arrays.asList(new Cuestionario(5L,"Programación"),
//							new Cuestionario(6L, "Analisis Sistemas"),
//							new Cuestionario(7L, "Matematicas Discretas"));
	}

	@Override
	public Cuestionario guardar(Cuestionario cuestionario) {
		System.out.println("Ejecion de metodo - guardar");
		return cuestionario;
	}

}
