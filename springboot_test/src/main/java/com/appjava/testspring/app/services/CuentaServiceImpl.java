package com.appjava.testspring.app.services;

import java.math.BigDecimal;

import com.appjava.testspring.app.models.Banco;
import com.appjava.testspring.app.models.Cuenta;
import com.appjava.testspring.app.repositories.BancoRepository;
import com.appjava.testspring.app.repositories.CuentaRepository;

public class CuentaServiceImpl implements CuentaService {
	
	private CuentaRepository cuentaRepository;
	private BancoRepository bancoRepository;
	
	

	public CuentaServiceImpl(CuentaRepository cuentaRepository, BancoRepository bancoRepository) {
		this.cuentaRepository = cuentaRepository;
		this.bancoRepository = bancoRepository;
	}

	@Override
	public Cuenta findById(Long id) {
		return cuentaRepository.findById(id);
	}

	@Override
	public int revisarTotalTransferencias(Long bancoId) {
		Banco banco = bancoRepository.findById(bancoId);
		return banco.getTotalTransferencias();
	}

	@Override
	public BigDecimal revisarSaldo(Long cuentaId) {
		Cuenta cuenta = cuentaRepository.findById(cuentaId);
		return cuenta.getSaldo();
	}

	@Override
	public void transferir(Long numCuentaOrigen, Long numCuentaDestino, BigDecimal monto,
			Long bancoId) {
		Banco banco = bancoRepository.findById(bancoId);   // 1L o bancoId se deberia obtener de la cuenta del usuario en hambiente real
		int totaltransferencias = banco.getTotalTransferencias();
		banco.setTotalTransferencias(++totaltransferencias);
		bancoRepository.update(banco);
		
		Cuenta cuentaOrigen = cuentaRepository.findById(numCuentaOrigen);
		cuentaOrigen.debito(monto);
		cuentaRepository.update(cuentaOrigen);
		
		Cuenta cuentaDestino = cuentaRepository.findById(numCuentaDestino);
		cuentaDestino.credito(monto);
		cuentaRepository.update(cuentaDestino);
		
	}

}
