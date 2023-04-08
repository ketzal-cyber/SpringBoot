package com.appjava.testspring.app;

import java.math.BigDecimal;

import com.appjava.testspring.app.models.Banco;
import com.appjava.testspring.app.models.Cuenta;

public class Datos {
	
	public static final Cuenta CUENTA_001 = new Cuenta(1L, "Erika", new BigDecimal("1000"));
	public static final Cuenta CUENTA_002 = new Cuenta(2L, "Jhon", new BigDecimal("2000"));
	public static final Banco BANCO = new Banco(1L, "Financiero", 0);

}
