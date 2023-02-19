package appjava_junit.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GeneralUtil {

	private static Properties prop = null;
	private static Properties propLog = null;
	
	// Este método se encarga de leer las propiedades del archivo "GeneralParams.properties"
	public static String getProperty(String key) {
		if(prop == null)
			prop = getPropertyFile();
		return prop.getProperty(key);
	}
	
	// Este método se encarga de cargar el objeto de propiedades "GeneralParams.properties"
	private static Properties getPropertyFile() {
		 // InputStream is = null;
		try {
			// is = new FileInputStream("E:\Disco E\Workspace\Workspace indigo\TEST\Log4j\src\GeneralParams.properties");
			// prop.load(is);
			prop = new Properties();
			prop.load(GeneralUtil.class.getResourceAsStream("/GeneralParams.properties"));
			return prop;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		}
	}
	
	
	// Este método se encarga de cargar el objeto de propiedades "log4j.properties"
	public static Properties getPropertyFile(String property) {
		/*
		   * Valida si el properties es nulo. En ese caso, lee del parámetro, sino retorna
		   * el properties ya leido
		 */
		if(propLog == null) {
			// lo obtiene en un is para poder validarlo
			InputStream is = GeneralUtil.class.getResourceAsStream(property);
			propLog = new Properties();
			if(is != null) { // si lo encontró muestra un mensaje en syso (ya que este prop esta antes del log)
				try {
					propLog.load(is);
				} catch (IOException ioe) {
					System.out.println("ERROR: *** "+ ioe.getMessage());
				}
			} else {
				System.out.println("ERROR: *** "+ property + "no cargo***");
			 }
		}
		return propLog;
	}
	
	
	
	
}
