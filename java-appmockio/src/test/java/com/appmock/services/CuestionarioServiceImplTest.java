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
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
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
import org.mockito.InOrder;
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
import com.appmock.dao.CuestionarioPreguntaDaoImpl;
import com.appmock.models.Cuestionario;

/* -> *****-*
 * */
//@ExtendWith(MockitoExtension.class) pom mockito-junit-jupiter  42
public class CuestionarioServiceImplTest {
	
	@Mock
	CuestionarioDAO cuestionarioDao;
	@Mock
	CuestionarioPreguntaDAO cuestionarioPreguntaDao;
	
	@Mock
	CuestionarioDaoImpl cuestionarioDaoImpl;
	@Mock
	CuestionarioPreguntaDaoImpl cuestionarioPreguntaDaoImpl;
	
	@InjectMocks
	CuestionarioServiceImp cuestionarioService;
	
	@Captor
	ArgumentCaptor<Long> captor;
	
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
	
	// Validacion personalizadas con ArgumentMatchers 45 leccion
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
	
	
	// leccion47 Argument matchers part2
	
	@Test
	void ArgumentMatchers2(){
		when(cuestionarioDao.findAll()).thenReturn(Datos.EXAMENES); 	// EXAMENES_ID_NEGATIVOS 
		when(cuestionarioPreguntaDao.findPreguntasPorCuestionarioId(anyLong())).thenReturn(Datos.PREGUNTAS);
		
		cuestionarioService.findCuestionarioPorNombreConPreguntas("Programación");
		
		verify(cuestionarioDao).findAll();
//		
		verify(cuestionarioPreguntaDao).findPreguntasPorCuestionarioId(argThat(new OurArgumentMatchers()));
	}
	
	@Test
	void ArgumentMatchers3(){
		when(cuestionarioDao.findAll()).thenReturn(Datos.EXAMENES); 	// EXAMENES_ID_NEGATIVOS 
		when(cuestionarioPreguntaDao.findPreguntasPorCuestionarioId(anyLong())).thenReturn(Datos.PREGUNTAS);
		
		cuestionarioService.findCuestionarioPorNombreConPreguntas("Programación");
		
		verify(cuestionarioDao).findAll();
		// con exprecion labda		 sin poder implement el metod toString
		verify(cuestionarioPreguntaDao).findPreguntasPorCuestionarioId(argThat((argument) -> argument != null && argument > 0));
	}
	
	//implementar ArgMatch con una clase separada o anonima igual puede ser  una inner class
	public static class OurArgumentMatchers implements ArgumentMatcher<Long>{
		
		private Long argument;

		@Override
		public boolean matches(Long argument) {
			// Acemos algo como una validacion 
			// sea positivo y no sea null
			this.argument = argument;
			return argument != null && argument > 0;
		}

		//ventaja de utilizar este metodo de clase es personalizar el mensaje de error
		// para ocupar el argumento debe ser un propiedad
		@Override
		public String toString() {
			return "ArgumentMatchers mensaje personalizado "
					+ " donde argument "+ argument +" sea mayor a 0 y no sea null";
		}
	}
	
	/*
	 * Capturar los argumentos yy probarlos 
	 * ArgumentCaptor
	 **/
	@Test
	void testAgumentCaptor() {
		when(cuestionarioDao.findAll()).thenReturn(Datos.EXAMENES);
		//when(cuestionarioPreguntaDao.findPreguntasPorCuestionarioId(anyLong())).thenReturn(Datos.PREGUNTAS);
		
		cuestionarioService.findCuestionarioPorNombreConPreguntas("Programación");
		
		//ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);   // usando anotacion en propiedades
		// @Captor  propiedad ArgumentCaptor<Long> captor;
		verify(cuestionarioPreguntaDao).findPreguntasPorCuestionarioId(captor.capture());
		
		assertEquals(5L, captor.getValue());
	}
	
	/*
	 * Usando do... Throw Answer return
	 */
	@Test
	void testDoThrow() {
		Cuestionario cuestionario = Datos.cuestionario;
		cuestionario.setPreguntas(Datos.PREGUNTAS);
		doThrow(IllegalArgumentException.class).when(cuestionarioPreguntaDao).guardarVarias(anyList());
		
		assertThrows(IllegalArgumentException.class, () -> {
			cuestionarioService.guardar(cuestionario);
		});
	}
	
	// doAnswer
	@Test
	void testDoAnswer() {
		when(cuestionarioDao.findAll()).thenReturn(Datos.EXAMENES);
		//when(cuestionarioPreguntaDao.findPreguntasPorCuestionarioId(anyLong())).thenReturn(Datos.PREGUNTAS);  // hacer con doAnswer()
		doAnswer(invocacion -> {
			Long id = invocacion.getArgument(0);
			return id == 5l? Datos.PREGUNTAS: Collections.emptyList();		// null a collection
		}).when(cuestionarioPreguntaDao).findPreguntasPorCuestionarioId(anyLong());
		
		Cuestionario cuestionario = cuestionarioService.findCuestionarioPorNombreConPreguntas("Programación");
		
		assertEquals(5, cuestionario.getPreguntas().size());
		assertTrue(cuestionario.getPreguntas().contains("JAVA"));
		assertEquals(5L, cuestionario.getId());
		assertEquals("Programación", cuestionario.getNombre());
		
		verify(cuestionarioPreguntaDao).findPreguntasPorCuestionarioId(anyLong());
	}
	
	//hacer de otra forma guardarCuestionario
	@Test
	void testGuardarCuestionariooWithDoAnswer() {
		// GIVEN precondiciones enotrno impulsadoo al comportamiento 
		Cuestionario newCuestionario = new Cuestionario(8L,"ForenseTI");
		newCuestionario.setPreguntas(Datos.PREGUNTAS);
		
//		when(cuestionarioDao.guardar(any(Cuestionario.class))).thenReturn(Datos.cuestionario); generar id de forma automatica 
//		when(cuestionarioDao.guardar(any(Cuestionario.class))).then(new Answer<Cuestionario>() {
//			Long indice = 8L;
//			@Override
//			public Cuestionario answer(InvocationOnMock invocation) throws Throwable {
//				// invocation captura el cuestionario.class o el newCuestionario
//				Cuestionario cuestionario = invocation.getArgument(0);
//				cuestionario.setId(indice++);
//				return cuestionario;
//			}
//		});
		// implementar otra forma de haccerlo
		doAnswer(new Answer<Cuestionario>() {
			Long indice = 8L;
			@Override
			public Cuestionario answer(InvocationOnMock invocation) throws Throwable {
				Cuestionario cuestionario = invocation.getArgument(0);
				cuestionario.setId(indice++);
				return cuestionario;
			}
		}).when(cuestionarioDao).guardar(any(Cuestionario.class));
		
		//  When   cuando se ejecuta el metodo que queremos probar
		Cuestionario cuestionario = cuestionarioService.guardar(newCuestionario);
		
		// Then 
		assertNotNull(cuestionario.getId());
		assertEquals(8L, cuestionario.getId());
		assertEquals("ForenseTI", cuestionario.getNombre());
		
		verify(cuestionarioDao).guardar(any(Cuestionario.class));
		verify(cuestionarioPreguntaDao).guardarVarias(anyList());
	}
	
	// llamar al metodo real  no llamar al metodo simulado  doCallRealMethod
	@Test
	void testDoCallRealMethod() {
		//cuestionarioDaoImpl
		//cuestionarioPreguntaDaoImpl
		
		when(cuestionarioDao.findAll()).thenReturn(Datos.EXAMENES);
		//when(cuestionarioPreguntaDao.findPreguntasPorCuestionarioId(anyLong())).thenReturn(Datos.PREGUNTAS);
		
		//llamar al metodo real
		doCallRealMethod().when(cuestionarioPreguntaDaoImpl).findPreguntasPorCuestionarioId(anyLong());
		
		Cuestionario cuestionario = cuestionarioService.findCuestionarioPorNombreConPreguntas("Programación");
		assertEquals(5L, cuestionario.getId());
		assertEquals("Programación", cuestionario.getNombre());
	}
	
	/*
	 * Uso de los SPY y DoReturn
	 * spy es un hibrido un objeto mock y un real
	 * invocar sin definir ningun when, un simulacro
	 * 		sin mockear un metodo del spy
	 * cuando se invoca sera al metodo real
	 * no se tiene el control real de los metodos reales
	 */
	@Test
	void testSpy() {
		CuestionarioDAO cuestionarioDao = spy(CuestionarioDaoImpl.class);
		CuestionarioPreguntaDAO cuestionarioPreguntaDao = spy(CuestionarioPreguntaDaoImpl.class);
		CuestionarioService cuestionarioServie = new CuestionarioServiceImp(cuestionarioDao, cuestionarioPreguntaDao);
		
		//when llamando al metodo real imprimiendo los sout de los metodos para evitar eso usamos doReturn
		List<String> preguntas = Arrays.asList("aritmética");
//		when(cuestionarioPreguntaDao.findPreguntasPorCuestionarioId(anyLong())).thenReturn(preguntas); cambiando por doReturn
		doReturn(preguntas).when(cuestionarioPreguntaDao).findPreguntasPorCuestionarioId(anyLong());
		
		Cuestionario cuestionario = cuestionarioServie.findCuestionarioPorNombreConPreguntas("Programación");
		
		assertEquals(5, cuestionario.getId());
		assertEquals("Programación", cuestionario.getNombre());
		assertEquals(1, cuestionario.getPreguntas().size());
		assertTrue(cuestionario.getPreguntas().contains("aritmética"));  //llamando al pregntas localdel metodo
		
		verify(cuestionarioDao).findAll();
		verify(cuestionarioPreguntaDao).findPreguntasPorCuestionarioId(anyLong());
	}
	
	
	
	/* Otra caracteristica de Mock 
	 * Verificar el orden en el cual se eecutan los metodos mock
	 */
	@Test
	void testOrdenDeInvocacion() {
		
		when(cuestionarioDao.findAll()).thenReturn(Datos.EXAMENES);
		
		cuestionarioService.findCuestionarioPorNombreConPreguntas("Programación");
		cuestionarioService.findCuestionarioPorNombreConPreguntas("Analisis Sistemas");
		
		InOrder inOrder = Mockito.inOrder(cuestionarioPreguntaDao);
		inOrder.verify(cuestionarioPreguntaDao).findPreguntasPorCuestionarioId(5L);
		inOrder.verify(cuestionarioPreguntaDao).findPreguntasPorCuestionarioId(6L);
	}
	
	// veriicar multiplas mocks
	@Test
	void testOrdenDeInvocacion2() {
		
		when(cuestionarioDao.findAll()).thenReturn(Datos.EXAMENES);
		
		cuestionarioService.findCuestionarioPorNombreConPreguntas("Programación");
		cuestionarioService.findCuestionarioPorNombreConPreguntas("Analisis Sistemas");
		
		InOrder inOrder = Mockito.inOrder(cuestionarioDao, cuestionarioPreguntaDao);
		inOrder.verify(cuestionarioDao).findAll();
		inOrder.verify(cuestionarioPreguntaDao).findPreguntasPorCuestionarioId(5L);
		
		inOrder.verify(cuestionarioDao).findAll();
		inOrder.verify(cuestionarioPreguntaDao).findPreguntasPorCuestionarioId(6L);
	}
	
	/*
	 * Verificar el número de invocaciones de los mocks
	 */
	@Test
	void testNumeroInvocaciones() {
		when(cuestionarioDao.findAll()).thenReturn(Datos.EXAMENES);
		cuestionarioService.findCuestionarioPorNombreConPreguntas("Programación");
		
		verify(cuestionarioPreguntaDao).findPreguntasPorCuestionarioId(5L);   // por defecto se ejecuta 1 sola ves findPreguntasPorCuestionarioId
		verify(cuestionarioPreguntaDao, times(1)).findPreguntasPorCuestionarioId(5L); // con 2 no pasa xq solo se invoca 1 ves
		verify(cuestionarioPreguntaDao, atLeast(1)).findPreguntasPorCuestionarioId(5L);   // al menos que se ejecute 1 sola ves o como minimo 1
		verify(cuestionarioPreguntaDao, atLeastOnce()).findPreguntasPorCuestionarioId(5L);   // alo menos 1 ves equivalente a atleast(1)
		verify(cuestionarioPreguntaDao, atMost(5)).findPreguntasPorCuestionarioId(5L);   // al maximo se ejecutara tentas veces
		verify(cuestionarioPreguntaDao, atMostOnce()).findPreguntasPorCuestionarioId(5L);   // equivalente a atMost(1)
	}
	
	@Test
	void testNumeroInvocaciones2() {
		when(cuestionarioDao.findAll()).thenReturn(Datos.EXAMENES);
		cuestionarioService.findCuestionarioPorNombreConPreguntas("Programación");
		
		// si se ejecutara dos veces el metodo findPreguntasPorCuestionarioId
//		verify(cuestionarioPreguntaDao, times(2)).findPreguntasPorCuestionarioId(5L); 
//		verify(cuestionarioPreguntaDao, atLeast(2)).findPreguntasPorCuestionarioId(5L);  
//		verify(cuestionarioPreguntaDao, atLeastOnce()).findPreguntasPorCuestionarioId(5L); 
//		verify(cuestionarioPreguntaDao, atMost(20)).findPreguntasPorCuestionarioId(5L); 
	}
	
	
	@Test
	void testNumeroInvocaciones3() {
		when(cuestionarioDao.findAll()).thenReturn(Collections.emptyList());
		cuestionarioService.findCuestionarioPorNombreConPreguntas("Programación");
		
		// nunca se invoca
		verify(cuestionarioPreguntaDao, never()).findPreguntasPorCuestionarioId(5L);
		verifyNoInteractions(cuestionarioPreguntaDao);
		
		verify(cuestionarioDao).findAll();
		 
	}

}
