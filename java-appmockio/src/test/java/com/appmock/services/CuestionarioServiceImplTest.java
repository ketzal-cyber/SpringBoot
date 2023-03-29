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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import com.appmock.dao.CuestionarioDAO;
import com.appmock.dao.CuestionarioDaoImpl;
import com.appmock.dao.CuestionarioPreguntaDAO;
import com.appmock.models.Cuestionario;

/* -> *****-*
 * */
//@ExtendWith(MockitoExtension.class) pom mockito-junit-jupiter  42
public class CuestionarioServiceImplTest {
	
	@Mock
	CuestionarioDAO cuestionarioDao;
	@Mock
	CuestionarioPreguntaDAO cuestionarioPreguntaDao;
	
	@InjectMocks
	CuestionarioServiceImp cuestionarioService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // abilitar anotacion de mock
		//camiar por anotaciones de mock
//		cuestionarioDao = Mockito.mock(CuestionarioDAO.class);
//		cuestionarioPreguntaDao = mock(CuestionarioPreguntaDAO.class);
//		cuestionarioService = new CuestionarioServiceImp(cuestionarioDao,cuestionarioPreguntaDao);
	}
	
	@Test
	void findCuestionarioPorNombre() {
		//CuestionarioDAO cuestionarioDao = new CuestionarioDaoImpl(); // linea para llamar a clase simulando acceso a datos
		// no puedo hacer un Mock de un metodo privado o static ni final
//		CuestionarioDAO cuestionarioDao = Mockito.mock(CuestionarioDAO.class);   // mover a atriutos de calse para reutilizar
//		CuestionarioService cuestionarioService = new CuestionarioServiceImp(cuestionarioDao);  	// iniciarlos en @BeoreEach
		
//		List<Cuestionario> datos = Arrays.asList(new Cuestionario(5L,"Programación"),  // remplazado por la clase de Datos
//				new Cuestionario(6L, "Analisis Sistemas"),
//				new Cuestionario(7L, "Matematicas Discretas"));
		
		when(cuestionarioDao.findAll()).thenReturn(Datos.EXAMENES);
		
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
	
	@Test
	void testPreguntaCuestionario() {
		when(cuestionarioDao.findAll()).thenReturn(Datos.EXAMENES);
		when(cuestionarioPreguntaDao.findPreguntasPorCuestionarioId(anyLong())).thenReturn(Datos.PREGUNTAS);
		
		Cuestionario cuestionario = cuestionarioService.findCuestionarioPorNombreConPreguntas("Programación");
		
		assertEquals(5, cuestionario.getPreguntas().size());
		assertTrue(cuestionario.getPreguntas().contains("Logica"));
	}
	
	@Test
	void testPreguntaCuestionarioVerificar() {
		when(cuestionarioDao.findAll()).thenReturn(Datos.EXAMENES);
		when(cuestionarioPreguntaDao.findPreguntasPorCuestionarioId(anyLong())).thenReturn(Datos.PREGUNTAS);
		
		Cuestionario cuestionario = cuestionarioService.findCuestionarioPorNombreConPreguntas("Programación");
		
		assertEquals(5, cuestionario.getPreguntas().size());
		verify(cuestionarioDao).findAll();
		verify(cuestionarioPreguntaDao).findPreguntasPorCuestionarioId(anyLong());
	}
	
	
	@Test
	void testCuestionarioNoExisteVerificar() {
		// GIVE
		when(cuestionarioDao.findAll()).thenReturn(Datos.EXAMENES); // arumento Collections.emptyList() 
		when(cuestionarioPreguntaDao.findPreguntasPorCuestionarioId(anyLong())).thenReturn(Datos.PREGUNTAS);
		
		// WHEN
		Cuestionario cuestionario = cuestionarioService.findCuestionarioPorNombreConPreguntas("Programación2"); // modificado para que falle
		
		// THEN
		assertNull(cuestionario);
		verify(cuestionarioDao).findAll();
//		verify(cuestionarioPreguntaDao).findPreguntasPorCuestionarioId(anyLong());
	}
	
	@Test
	void testGuardarCuestionario() {
		// GIVEN precondiciones enotrno impulsadoo al comportamiento 
		Cuestionario newCuestionario = new Cuestionario(8L,"ForenseTI");
		newCuestionario.setPreguntas(Datos.PREGUNTAS);
		
//		when(cuestionarioDao.guardar(any(Cuestionario.class))).thenReturn(Datos.cuestionario); generar id de forma automatica 
		when(cuestionarioDao.guardar(any(Cuestionario.class))).then(new Answer<Cuestionario>() {
			Long indice = 8L;
			@Override
			public Cuestionario answer(InvocationOnMock invocation) throws Throwable {
				// invocation captura el cuestionario.class o el newCuestionario
				Cuestionario cuestionario = invocation.getArgument(0);
				cuestionario.setId(indice++);
				return cuestionario;
			}
		});
		//  When   cuando se ejecuta el metodo que queremos probar
		Cuestionario cuestionario = cuestionarioService.guardar(newCuestionario);
		
		// Then 
		assertNotNull(cuestionario.getId());
		assertEquals(8L, cuestionario.getId());
		assertEquals("ForenseTI", cuestionario.getNombre());
		
		verify(cuestionarioDao).guardar(any(Cuestionario.class));
		verify(cuestionarioPreguntaDao).guardarVarias(anyList());
	}
	
	
	// Comprobacion de  Excepciones usando When y ThenThrow
	@Test
	void testManejoException() {
//		when(cuestionarioDao.findAll()).thenReturn(Datos.EXAMENES);
		when(cuestionarioDao.findAll()).thenReturn(Datos.EXAMENES_ID_NULL); 	// opcion con null
		
																				// manejar propias exceptioes de runtime o excetion
																	// isNull siempre arroja un IllegalArgumentException
		when(cuestionarioPreguntaDao.findPreguntasPorCuestionarioId(isNull())).thenThrow(IllegalArgumentException.class);
		
		// Exception excepption =  assertThrows . . .
		assertThrows(IllegalArgumentException.class, () -> {
			cuestionarioService.findCuestionarioPorNombreConPreguntas("Programación");
		});
		
		verify(cuestionarioDao).findAll();
		verify(cuestionarioPreguntaDao).findPreguntasPorCuestionarioId(isNull());  // cambio de null a isNull
	}
	
	// Validacion personalizadas con ArgumentMatchers
	// asegurar que ciertos arguemntos se pasan a los mocks
	@Test
	void ArgumentMatchers(){
		when(cuestionarioDao.findAll()).thenReturn(Datos.EXAMENES);
		when(cuestionarioPreguntaDao.findPreguntasPorCuestionarioId(anyLong())).thenReturn(Datos.PREGUNTAS);
		
		cuestionarioService.findCuestionarioPorNombreConPreguntas("Programación");
		
		/*ArgumentMatchers es para veriicacion cuando se llaman los metodos mocks los 
		 * verify(cuestionarioPreguntaDao).findPreguntasPorCuestionarioId(5L)
		 * verify(cuestionarioPreguntaDao).findPreguntasPorCuestionarioId(eq(5L));
		 * Lambda mas robusto
		 */
		verify(cuestionarioDao).findAll();
//		verify(cuestionarioPreguntaDao).findPreguntasPorCuestionarioId(argThat(arg -> arg.equals(5L)));
//		verify(cuestionarioPreguntaDao).findPreguntasPorCuestionarioId(argThat(arg -> arg != null && arg > 5L));
		verify(cuestionarioPreguntaDao).findPreguntasPorCuestionarioId(argThat(arg -> arg != null && arg.equals(5L)));
		
	}

}
