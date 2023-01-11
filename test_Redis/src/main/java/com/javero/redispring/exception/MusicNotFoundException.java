package com.javero.redispring.exception;

public class MusicNotFoundException extends RuntimeException {

	public MusicNotFoundException() {
		super();
	}
	
	public MusicNotFoundException(String message) {
		super(message);
	}
	
	public MusicNotFoundException(Long id) {
		super("Music not found: "+ id);
	}
}
