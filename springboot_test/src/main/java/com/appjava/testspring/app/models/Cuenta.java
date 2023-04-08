package com.appjava.testspring.app.models;

import java.math.BigDecimal;

import com.appjava.testspring.app.exceptions.DineroInsuficienteException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cuenta {
	
	private Long id;
	private String persona;
	private BigDecimal saldo;
	
	public void debito(BigDecimal monto) {
		BigDecimal nuevoSaldo = this.saldo.subtract(monto);
		if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
			throw new DineroInsuficienteException("Dinero insuficiente en la cuenta");
		}
		this.saldo = nuevoSaldo;
	}
	
	public void credito(BigDecimal monto) {
		this.saldo = saldo.add(monto);
	}

}
