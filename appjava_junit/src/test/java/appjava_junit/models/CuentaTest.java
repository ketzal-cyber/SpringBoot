package appjava_junit.models;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import appjava_junit.exceptions.SaldoInsuficienteException;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS) // creacion de una sola instancia de test podiento quitar static de BeforeAll
public class CuentaTest {
	// private staatic final
	// Private static final Logger LOGGER = Logger

	Cuenta cuenta;
	TestInfo testInfo;
	TestReporter testReporter;

	@BeforeEach
	void initMetodoTest(TestInfo testInfo, TestReporter testReporter) {
		cuenta = new Cuenta("Anyel", new BigDecimal("1000.12345"));
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		System.out.println("Iniciando el metodo de prueba");
		testReporter.publishEntry("ejecutando: "+ testInfo.getDisplayName() + " "+ testInfo.getTestMethod().orElse(null).getName()
				+ " con las etiquetas "+ testInfo.getTags());  // salida con formato de tipo reprote
	}

	@AfterEach
	void aftertest() {
		System.out.println("Finalizando el metodo de prueba");
	}

	@BeforeAll
	static void beforeAll() {
		System.out.println("*** Inicializando  la clase de Test");
	}

	@AfterAll
	static void afterAll() {
		System.out.println(">>> Finalizando  la clase de Test");
	}

	@Tag("cuenta")
	@Nested
	@DisplayName("Testeando atriutos de cuenta")
	class CuentaTestNombreSaldo {
		//@Disabled
		@Test
		@DisplayName("Metodo test nombre cuenta")
		void testCuentaPersona() { // añadiendo inyeccion de dependencias de junit5 como argumentos al metodo TestInfo testInfo, TestReporter testReporter
			// cuenta.setPersona("Angel");
//			System.out.println("ejecutando: "+ testInfo.getDisplayName() + " "+ testInfo.getTestMethod().orElse(null).getName()
//									+ " con las etiquetas "+ testInfo.getTags());   // lineas para obtener info con testInfo o testRepo
			String esperado = "Anye";
			String real = cuenta.getPersona();
			assertEquals(esperado, real, "eL VALOR ESPERADO NO ES EL MISMO"); // AGREGAND MENSAES DE FORMA LITERAL
			assertTrue(real.equals("Anyel"), () -> "Mensaje con lamda"); // AGREGANDO LAMBDA PARA MENSAJES
		}

		@Test
		void testCuentaSaldo() {
			assertEquals(1000.12345, cuenta.getSaldo().doubleValue());
		}
	}

	// test de clase CuentaTestNombreSaldo

	@Nested
	class OperacionesCuentaTest {
		
		@Tag("cuenta")
		@Test
		void testDebitoCuenta() {
			// Cuenta cuenta = new Cuenta("Anyel", null);
			cuenta.debito(new BigDecimal(100));
			assertNotNull(cuenta.getSaldo());
			assertEquals(900, cuenta.getSaldo().intValue());
			assertEquals("900.12345", cuenta.getSaldo().toPlainString());
		}

		@Tag("cuenta")
		@Test
		void testCreditoCuenta() {
			// Cuenta cuenta = new Cuenta("Anyel", null);
			cuenta.credito(new BigDecimal(200));
			assertNotNull(cuenta.getSaldo());
			assertEquals(1200, cuenta.getSaldo().intValue());
			assertEquals("1200.12345", cuenta.getSaldo().toPlainString());
		}

		@Tag("cuenta")
		@Tag("banco")
		@Test
		void testTransferir() {
			Cuenta cuenta2 = new Cuenta("Alina", new BigDecimal("2500.12345"));

			Banco bank = new Banco();
			bank.setNombre("Bank");
			bank.transferir(cuenta2, cuenta, new BigDecimal(500));

			assertEquals("2000.12345", cuenta2.getSaldo().toPlainString());
			assertEquals("1500.12345", cuenta.getSaldo().toPlainString());
		}
	}

	// test de OperacionesCuentaTest
	@Tag("cuenta")
	@Tag("error")
	@Test
	void saldoInsuficiente() {
		Exception exception = assertThrows(SaldoInsuficienteException.class, () -> {
			cuenta.debito(new BigDecimal(1500));
		}); // testear exceptions

		String actual = exception.getMessage();
		String esperado = "Saldo insuficiente";
		assertEquals(esperado, actual); // testear mensajes de exception
	}

	@Tag("cuenta")
	@Tag("banco")
	@Test
	@DisplayName("Test de relacion de entidades")
	void testCuentasRelacion() {
		Cuenta cuenta = new Cuenta("Anyel", new BigDecimal("1500.12345"));
		Cuenta cuenta2 = new Cuenta("Alina", new BigDecimal("2500.12345"));

		Banco bank = new Banco();
		bank.addCuenta(cuenta);
		bank.addCuenta(cuenta2);
		bank.setNombre("Bank");
		bank.transferir(cuenta2, cuenta, new BigDecimal(500));

		// assertAll(()-> {}, ()-> {}, ()-> {}, ()-> {}, ()-> {}, ()-> {}, ()-> {});
		assertAll(() -> {
			assertEquals("2000.12345", cuenta2.getSaldo().toPlainString());
		}, () -> {
			assertEquals("2000.12345", cuenta.getSaldo().toPlainString());
		}, () -> {
			assertEquals(2, bank.getCuentas().size());
		}, () -> {
			assertEquals("Bank", cuenta.getBanco().getNombre());
		}, () -> {
			assertEquals("Alina", bank.getCuentas().stream().filter(cuent -> cuent.getPersona().equals("Alina"))
					.findFirst().get().getPersona());
		}, () -> {
			assertTrue(bank.getCuentas().stream().filter(cuent -> cuent.getPersona().equals("Alina")).findFirst()
					.isPresent());
		}, () -> {
			assertTrue(bank.getCuentas().stream() // fforma mas simplificada
					.anyMatch(c -> c.getPersona().equals("Anyel")));
		});
	}

	// movidas a clase anidada SistemasOperativosTest{}
//	@Test
//	@EnabledOnOs(OS.WINDOWS)
//	void testSoloWindows() {
//	}
//	
//	@Test
//	@EnabledOnOs({OS.LINUX, OS.MAC})
//	void testSoloLinuxMac() {
//	}
//	
//	@Test
//	@DisabledOnOs(OS.WINDOWS)
//	void testNoWindows() {
//	}

	// movida a clase anidada JavaVersionTest
//	@Test
//	@EnabledOnJre(JRE.JAVA_11)
//	void testSoloJdk8() {
//	}

	// movida a clase anidada sistemProperties
//	@Test
//	void imprimirSystemProperties() {
//		Properties properties = System.getProperties();
//		properties.forEach((k, v) -> System.out.println(k + ":"+ v));
//	}
//	
//	@Test
//	@EnabledIfSystemProperty(named = "java.version", matches = ".*17.*")
//	void testJavaVersion() {
//	}
//	
//	@Test
//	@DisabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
//	void testNoArch64() {
//	}
//	
//	@Test
//	@EnabledIfSystemProperty(named = "ENV", matches = "dev") // solo cuando sea en amiente de dev
//	void testSoloDev() {
//	}

	// movida a clase anidada varialesAmiente
//	@Test
//	void imprimirVariablesAmiente() {
//		Map<String, String> getEnv = System.getenv();
//		getEnv.forEach((k, v)-> System.out.println(k + ":" + v));
//	}
//	
//	@Test
//	@EnabledIfEnvironmentVariable(named = "JAVA_HOME", matches = ".*jdk1.8.0_202.*")
//	void testJavaHome() {
//	}
//	
//	@Test
//	@EnabledIfEnvironmentVariable(named = "NUMBER_OF_PROCESSORS", matches = "4")
//	void testNumberProcessor() {
//	}
//	
//	@Test
//	@EnabledIfEnvironmentVariable(named = "ENVIROMENT", matches = "dev")  // solo testear cuando haya variable de amiente dev o produccion
//	void testVarialeAmienteDev() {
//	}
//	
//	@Test
//	@DisabledIfEnvironmentVariable(named = "ENVIROMENT", matches = "PROD")  // Desabilitar solo testear cuando haya variable de amiente PROD o produccion
//	void testDesabilitaVarialeAmiente() {
//	}

	// test Assumptions
	// ejecutar asumir valor de verdad
	// se ejecuta lo que esta despues de la linea
	@Disabled
	@Test
	void testAssumptions() {
		boolean esDev = "dev".equals(System.getProperty("ENV")); // amiente de desarrollo
		assumeTrue(esDev); // se ejecuta si esw verdadero que la variable es dev de la property ENV
		/*
		 * lineas que se ejecutan si es verdaddero
		 */
	}

	@Test
	void testAssumptions2() {
		boolean esDev = "dev".equals(System.getProperty("ENV")); // amiente de desarrollo
		assumingThat(esDev, () -> {
			/*
			 * lineas que se ejecutan si es verdaddero
			 */
		}); // se ejecuta si esw verdadero que la variable es dev de la property ENV
		// puede haber mas assert
	}

	/*
	 * Clases anidadas para organizar los metodos inner class organizar de forma
	 * gerarquica
	 */
	@Nested
	class SistemasOperativosTest {
		@Test
		@EnabledOnOs(OS.WINDOWS)
		void testSoloWindows() {
		}

		@Test
		@EnabledOnOs({ OS.LINUX, OS.MAC })
		void testSoloLinuxMac() {
		}

		@Test
		@DisabledOnOs(OS.WINDOWS)
		void testNoWindows() {
		}
	}

	// clase anidada jdk java
	@Nested
	class JavaVersionTest {
		@Test
		@EnabledOnJre(JRE.JAVA_11)
		void testSoloJdk8() {
		}
	}

	// clase para propiedades de sistema
	@Nested
	class SistemProperties {
		@Test
		void imprimirSystemProperties() {
			Properties properties = System.getProperties();
			properties.forEach((k, v) -> System.out.println(k + ":" + v));
		}

		@Test
		@EnabledIfSystemProperty(named = "java.version", matches = ".*17.*")
		void testJavaVersion() {
		}

		@Test
		@DisabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
		void testNoArch64() {
		}

		@Test
		@EnabledIfSystemProperty(named = "ENV", matches = "dev") // solo cuando sea en amiente de dev
		void testSoloDev() {
		}
	}

	// clase Variable Amiente
	@Nested
	class VariableAmiente {
		@Test
		void imprimirVariablesAmiente() {
			Map<String, String> getEnv = System.getenv();
			getEnv.forEach((k, v) -> System.out.println(k + ":" + v));
		}

		@Test
		@EnabledIfEnvironmentVariable(named = "JAVA_HOME", matches = ".*jdk1.8.0_202.*")
		void testJavaHome() {
		}

		@Test
		@EnabledIfEnvironmentVariable(named = "NUMBER_OF_PROCESSORS", matches = "4")
		void testNumberProcessor() {
		}

		@Test
		@EnabledIfEnvironmentVariable(named = "ENVIROMENT", matches = "dev") // solo testear cuando haya variable de
																				// amiente dev o produccion
		void testVarialeAmienteDev() {
		}

		@Test
		@DisabledIfEnvironmentVariable(named = "ENVIROMENT", matches = "PROD") // Desabilitar solo testear cuando haya
																				// variable de amiente PROD o produccion
		void testDesabilitaVarialeAmiente() {
		}
	}

	// repetir un test
	@RepeatedTest(value = 3, name = "Repetición número {currentRepetition} de {totalRepetitions}")
	void testDebitoCuentaRepetir(RepetitionInfo info) {

		if (info.getCurrentRepetition() == 2) {
			System.out.println(">>>>>>>>Estamos en la repeticion  " + info.getCurrentRepetition());
		}
		// Cuenta cuenta = new Cuenta("Anyel", null);
		cuenta.debito(new BigDecimal(100));
		assertNotNull(cuenta.getSaldo());
		assertEquals(900, cuenta.getSaldo().intValue());
		assertEquals("900.12345", cuenta.getSaldo().toPlainString());
	}

	@Tag("param")  // dar etiqueta para organizar la ejecucion  
	class TestParametrizadas {
		// repetir un test con Parametrizadas
		@ParameterizedTest(name = "Num {index} ejecion con valor {0} -> {argumentsWithNames}") // anotacion principal   // con // name valores
		@ValueSource(strings = { "100", "200", "300", "500", "700", "1000" }) // puede ser int o double
		void testDebitoCuentaValueSource(String montos) { // camiar tipo de dato si es int o double
			cuenta.debito(new BigDecimal(montos));
			assertNotNull(cuenta.getSaldo());
			assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
		}

		// repetir un test con Parametrizadas
		@ParameterizedTest(name = "Num {index} ejecion con valor {0} -> {argumentsWithNames}")
		@CsvSource({ "1,100", "2,200", "3,300", "4,500", "5,700", "6,1000" })
		void testDebitoCuentaCsvSource(String index, String montos) { // indix 1, montos podiendo imprimir
			cuenta.debito(new BigDecimal(montos));
			assertNotNull(cuenta.getSaldo());
			assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
		}

		// pasando varios argumentos por comas
		@ParameterizedTest(name = "Num {index} ejecion con valor {0} -> {argumentsWithNames}")
		@CsvSource({ "250,100, Juan, Juan", "250,200,Paola,Paola", "310,300,Sara,Sara", "510,500,Jannet,Jannet",
				"750,700,Maria,Maria", "1100,1000,Norma,Norma" })
		void testDebitoCuentaCsvSourceArumentos(String saldo, String montos, String esperado, String actual) {
			System.out.println(saldo + " -> " + montos);
			cuenta.setSaldo(new BigDecimal(saldo));
			cuenta.debito(new BigDecimal(montos));
			cuenta.setPersona(actual);

			assertNotNull(cuenta.getSaldo());
			assertNotNull(cuenta.getPersona());
			assertEquals(esperado, actual);
			assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
		}

		// repetir un test con Parametrizadas
		@ParameterizedTest(name = "Num {index} ejecion con valor {0} -> {argumentsWithNames}")
		@CsvFileSource(resources = "/data.csv")
		void testDebitoCuentaCsvFileSource(String montos) { // indix 1, montos podiendo imprimir
			cuenta.debito(new BigDecimal(montos));
			assertNotNull(cuenta.getSaldo());
			assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
		}

		// repetir un test con Parametrizadas
		@ParameterizedTest(name = "Num {index} ejecion con valor {0} -> {argumentsWithNames}")
		@CsvFileSource(resources = "/data2.csv")
		void testDebitoCuentaCsvFileSource2(String saldo, String montos, String esperado, String actual) {
			cuenta.setSaldo(new BigDecimal(saldo));
			cuenta.debito(new BigDecimal(montos));
			cuenta.setPersona(actual);

			assertNotNull(cuenta.getSaldo());
			assertNotNull(cuenta.getPersona());
			assertEquals(esperado, actual);
			assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
		}
	}

	// repetir un test con Parametrizadas
	@Tag("param")
	@ParameterizedTest(name = "Num {index} ejecion con valor {0} -> {argumentsWithNames}")
	@MethodSource("montoList")
	void testDebitoCuentaMethodSource(String montos) { // indix 1, montos podiendo imprimir
		cuenta.debito(new BigDecimal(montos));
		assertNotNull(cuenta.getSaldo());
		assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
	}

	static List<String> montoList() {
		return Arrays.asList("100", "200", "300", "500", "700", "1000");
	}
	
	// clase para TimeOut
	@Nested
	@Tag("timeout")
	class TesteandoTimeOut{
		@Test
		@Timeout(4)
		void testTimeOut() throws InterruptedException {
			TimeUnit.SECONDS.sleep(2);
		}
		
		@Test
		@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
		void testTimeOut2() throws InterruptedException {
			TimeUnit.SECONDS.sleep(1);
		}
		
		@Test
		void testTimeOutAssertions() {
			assertTimeout(Duration.ofSeconds(5), () -> {
				TimeUnit.SECONDS.sleep(4);
			});
			
		}
		
		
	}
	
	/* Ejecutar prueas unitarias desde terminal 
	 * Maven SUREFIRE
	 * agregar plugin Template en el pom
	 * <build>
	 * 		<plugins>
	 * 			<plugin>
	 * 				<groupId>org.apache.maven.plugins</groupId>
	 * 				<artifactId>maven-surefire-plugin</artifactId>
	 * 				<version>2.22.2</version>
	 * 				<configuration>
	 * 					<groupId>cuenta</groupId>  agregar solo el tag cuenta en el test solo se ejecuta ese tag
	 * 				</configuration>
	 * 			<plugin>
	 * 		</plugins>
	 * </build>
	 * 
	 * mvn test 
	 * */

}
