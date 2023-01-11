package com.javero.redispring.service;

import java.util.Optional;
import java.util.Set;

import com.javero.redispring.entity.Music;

public interface MusikService {
	
	Set<Music> findAll();
	Optional<Music> findById(Long id);
	Set<Music> findByInterprete(String interprete);
	Set<Music> findByCompositor(String compositor);
	Music addMusic(Music music);
	Music modifyMusic(Long id, Music NewMusic);
	void deleteMusic(Long id);
	

}
