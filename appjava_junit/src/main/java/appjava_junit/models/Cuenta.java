package appjava_junit.models;

import java.math.BigDecimal;

import appjava_junit.exceptions.SaldoInsuficienteException;

public class Cuenta {
	
	private String persona;
	private BigDecimal saldo;
	private Banco banco;
	
	public Cuenta(String persona, BigDecimal saldo) {
		this.persona = persona;
		this.saldo = saldo;
	}
	
	
	public String getPersona() {
		return persona;
	}
	public void setPersona(String persona) {
		this.persona = persona;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	public Banco getBanco() {
		return banco;
	}


	public void setBanco(Banco banco) {
		this.banco = banco;
	}


	// metodos pendiente ss definir por TDD
	public void debito(BigDecimal monto) {
		BigDecimal nuevoSaldo = this.saldo.subtract(monto);
		if(nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
			throw new SaldoInsuficienteException("Saldo insuficiente");
		}
		this.saldo = nuevoSaldo;
	}
	
	public void credito(BigDecimal monto) {
		this.saldo = this.saldo.add(monto);
	}
	
	public boolean equeals(Object obj) {
		if(!(obj instanceof Cuenta)) {
			return false;
		}
		Cuenta cuentaObj = (Cuenta) obj;
		if(this.persona == null || this.saldo == null) {
			return false;
		}
		return this.persona.equals(cuentaObj.getPersona()) && this.saldo.equals(cuentaObj.getSaldo());
	}

}
