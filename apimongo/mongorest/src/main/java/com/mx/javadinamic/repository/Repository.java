package com.mx.javadinamic.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mx.javadinamic.entity.Empleado;

public interface Repository extends MongoRepository<Empleado, String> {

}
