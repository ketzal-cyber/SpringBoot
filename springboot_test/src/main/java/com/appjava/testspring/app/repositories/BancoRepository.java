package com.appjava.testspring.app.repositories;

import java.util.List;

import com.appjava.testspring.app.models.Banco;


public interface BancoRepository {
	
List<Banco> findAll();
	
	Banco findById(Long id);
	
	void update(Banco banco);
	
	// remove y delete

}
