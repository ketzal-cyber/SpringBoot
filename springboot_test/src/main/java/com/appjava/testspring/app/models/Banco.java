package com.appjava.testspring.app.models;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Banco {
	
	private Long id;
	private String nombre;
	private int totalTransferencias;

}
