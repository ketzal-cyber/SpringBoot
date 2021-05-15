package com.mx.javadinamic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mx.javadinamic.entity.Empleado;
import com.mx.javadinamic.service.Service;

@RestController
@RequestMapping(path = "/apirest")
public class Controller {
	
	@Autowired
	Service service;
	
	@RequestMapping(value = "/guardar",
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Empleado guardar(@RequestBody Empleado empleado) {
		return service.guardar(empleado);
	}
	
	@RequestMapping(value = "/actualizar",
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Empleado actualizar(@RequestBody Empleado empleado) {
		return service.actualiar(empleado);
	}
	
	@RequestMapping(value = "/consultarId",
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Empleado consultarId(@RequestBody Empleado empleado) {
		return service.consultarId(empleado);
	}
	
	@RequestMapping(value = "/borrar",
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean borrar(@RequestBody Empleado empleado) {
		return service.borrar(empleado);
	}
	
	
	@RequestMapping(value = "/listar",
			method = RequestMethod.GET)
	public @ResponseBody List<Empleado> listar() {
		return service.listar();
	}

}
