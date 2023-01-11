package com.javero.redispring.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javero.redispring.entity.Music;
import com.javero.redispring.exception.MusicNotFoundException;
import com.javero.redispring.service.MusikService;

@RestController
@RequestMapping("/")
public class MusicController {
	
	@Autowired
	private MusikService musicService;
	
	@PostMapping
	public ResponseEntity<Music> addMusic(@RequestBody Music music){
		Music musik = musicService.addMusic(music);
		return new ResponseEntity<>(musik, HttpStatus.CREATED);
	}
	
	@PutMapping("/music/{id}")
	public ResponseEntity<Music> modifyMusic(@PathVariable("id") Long id, @RequestBody Music newMusic){
		Music musik = musicService.modifyMusic(id, newMusic);
		return new ResponseEntity<>(musik, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/canciones")
	public ResponseEntity<Set<Music>> getMusics(){
		Set<Music> listMusic =  musicService.findAll();
		//HashSet<Music> hashListMsuic = new HashSet<Music>(listMusic);
		return new ResponseEntity<>(listMusic, HttpStatus.OK);
	}
	
	@GetMapping("/music/{id}")
	public ResponseEntity<?> getMusicById(@PathVariable("id") Long id){
		Optional<Music> musik = musicService.findById(id);
						//.orElseThrow(() -> new MusicNotFoundException(id)); //Not convert music a optional
		return new ResponseEntity<>(musik, HttpStatus.OK);
	}
	
	@DeleteMapping("/delmusic/{id}")
	public ResponseEntity<Response> deleteMusic(@PathVariable Long id){
		musicService.deleteMusic(id);
		return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
	}
	
	@ExceptionHandler(MusicNotFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Response> handleException(MusicNotFoundException mnfe){
		Response response = Response.errorResponse(Response.NOT_FOUND, mnfe.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

}
