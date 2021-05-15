package com.mx.javadinamic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mx.javadinamic.entity.Empleado;
import com.mx.javadinamic.repository.Repository;

@org.springframework.stereotype.Service
public class Service {
	
	@Autowired
	Repository repository;
	
	public Empleado guardar(Empleado empleado) {
		return repository.save(empleado);
	}
	
	public boolean borrar(Empleado empleado) {
			try {
				repository.delete(empleado);
				return true;
			} catch (Exception e) {
				return false;
			}
	}
	
	
	public Empleado actualiar(Empleado empleado) {
		return repository.save(empleado);
	}
	
	
	
	public Empleado consultarId(Empleado empleado) {
		return repository.findById(empleado.getId()).orElse(null);
	}
	
	public List<Empleado> listar(){
		return repository.findAll();
	}
	

}
