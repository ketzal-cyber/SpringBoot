package appjava_junit.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.core.Logger;

public class Log {
	// instancia de Logger
	//private static final Logger
	
	public static String getStackTrace(Exception ex) {
		
		StringWriter sWrite = new StringWriter();
		PrintWriter pWrite = new PrintWriter(sWrite);
		ex.printStackTrace(pWrite);
		return sWrite.toString();
	}

}
