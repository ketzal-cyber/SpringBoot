package com.appmock.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.internal.matchers.Any;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import com.appmock.dao.CuestionarioDAO;
import com.appmock.dao.CuestionarioDaoImpl;
import com.appmock.dao.CuestionarioPreguntaDAO;
import com.appmock.dao.CuestionarioPreguntaDaoImpl;
import com.appmock.models.Cuestionario;

/* -> *****-*
 * */
@ExtendWith(MockitoExtension.class)	//   mockito-junit-jupiter dependencia pom
public class CuestionarioServiceImplSpyTest {
	
	
	@Spy
	CuestionarioDaoImpl cuestionarioDaoImpl;
	@Spy
	CuestionarioPreguntaDaoImpl cuestionarioPreguntaDaoImpl;
	
	@InjectMocks
	CuestionarioServiceImp cuestionarioService;
	
	/*
	 * uso de anotacion para ailitar
	 * Uso de los SPY y DoReturn
	 * spy es un hibrido un objeto mock y un real
	 * invocar sin definir ningun when, un simulacro
	 * 		sin mockear un metodo del spy
	 * cuando se invoca sera al metodo real
	 * no se tiene el control real de los metodos reales
	 */
	@Test
	void testSpy() {
		//when llamando al metodo real imprimiendo los sout de los metodos para evitar eso usamos doReturn
		List<String> preguntas = Arrays.asList("aritmética");
//		when(cuestionarioPreguntaDao.findPreguntasPorCuestionarioId(anyLong())).thenReturn(preguntas); cambiando por doReturn
		doReturn(preguntas).when(cuestionarioPreguntaDaoImpl).findPreguntasPorCuestionarioId(anyLong());
		
		Cuestionario cuestionario = cuestionarioService.findCuestionarioPorNombreConPreguntas("Programación");
		
		assertEquals(5, cuestionario.getId());
		assertEquals("Programación", cuestionario.getNombre());
		assertEquals(1, cuestionario.getPreguntas().size());
		assertTrue(cuestionario.getPreguntas().contains("aritmética"));
		
		verify(cuestionarioDaoImpl).findAll();
		verify(cuestionarioPreguntaDaoImpl).findPreguntasPorCuestionarioId(anyLong());
			
	}
	

}
