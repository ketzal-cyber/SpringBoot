package com.appmock.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.appmock.dao.CuestionarioDAO;
import com.appmock.dao.CuestionarioDaoImpl;
import com.appmock.models.Cuestionario;

/* -> *****-*
 * */

public class CuestionarioServiceImplTest {
	
	CuestionarioDAO cuestionarioDao;
	CuestionarioService cuestionarioService;
	
	@BeforeEach
	void setUp() {
		cuestionarioDao = Mockito.mock(CuestionarioDAO.class);
		cuestionarioService = new CuestionarioServiceImp(cuestionarioDao);
	}
	
	@Test
	void findCuestionarioPorNombre() {
		//CuestionarioDAO cuestionarioDao = new CuestionarioDaoImpl(); // linea para llamar a clase simulando acceso a datos
		// no puedo hacer un Mock de un metodo privado o static ni final
//		CuestionarioDAO cuestionarioDao = Mockito.mock(CuestionarioDAO.class);   // mover a atriutos de calse para reutilizar
//		CuestionarioService cuestionarioService = new CuestionarioServiceImp(cuestionarioDao);  	// iniciarlos en @BeoreEach
		List<Cuestionario> datos = Arrays.asList(new Cuestionario(5L,"Programación"),
				new Cuestionario(6L, "Analisis Sistemas"),
				new Cuestionario(7L, "Matematicas Discretas"));
		
		when(cuestionarioDao.findAll()).thenReturn(datos);
		
		//Cuestionario cuestionario = cuestionarioService.findCuestionarioPorNombre("Programación");
//		assertNotNull(cuestionario);
//		assertEquals(5L, cuestionario.getId());
//		assertEquals("Programación", cuestionario.getNombre());
		
		Optional<Cuestionario> cuestionario = cuestionarioService.findCuestionarioPorNombre("Programación");
		assertTrue(cuestionario.isPresent());
		assertEquals(5L, cuestionario.orElseThrow().getId());
		assertEquals("Programación", cuestionario.get().getNombre());
	}
	
	
	@Test
	void findCuestionarioPorNombreListaVacia() {
		//
		List<Cuestionario> datos = Collections.emptyList();
		
		when(cuestionarioDao.findAll()).thenReturn(datos);
		
		Optional<Cuestionario> cuestionario = cuestionarioService.findCuestionarioPorNombre("Programación");
		assertFalse(cuestionario.isPresent());
		
	}

}
