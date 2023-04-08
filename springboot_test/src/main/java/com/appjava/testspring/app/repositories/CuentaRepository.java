package com.appjava.testspring.app.repositories;

import java.util.List;

import com.appjava.testspring.app.models.Cuenta;

public interface CuentaRepository {
	
	List<Cuenta> findAll();
	
	Cuenta findById(Long id);
	
	void update(Cuenta cuenta);
}
