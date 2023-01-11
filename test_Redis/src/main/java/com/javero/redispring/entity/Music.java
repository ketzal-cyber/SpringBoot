package com.javero.redispring.entity;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// Lomok
//@Data
//@AllArgsConstructor
//@NoArgsConstructor


@Entity
@Table(name = "musica")
public class Music implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4815928688092889374L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String interprete;
	private String compositor;
	private LocalTime duracion;

	public Music() {
		
	}
	
	

	public Music(Long id, String name, String interprete, String compositor, LocalTime duracion) {
		this.id = id;
		this.name = name;
		this.interprete = interprete;
		this.compositor = compositor;
		this.duracion = duracion;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInterprete() {
		return interprete;
	}

	public void setInterprete(String interprete) {
		this.interprete = interprete;
	}

	public String getCompositor() {
		return compositor;
	}

	public void setCompositor(String compositor) {
		this.compositor = compositor;
	}

	public LocalTime getDuracion() {
		return duracion;
	}

	public void setDuracion(LocalTime duracion) {
		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return "Music [id=" + id + ", name=" + name + ", artista=" + interprete + ", compositor=" + compositor
				+ ", duracion=" + duracion + "]";
	}
	
	
}
