package com.javero.redispring.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javero.redispring.entity.Music;
import com.javero.redispring.exception.MusicNotFoundException;
import com.javero.redispring.repository.MusicaRepository;

@Service
public class MusicServiceImpl implements MusikService{

	@Autowired
	private MusicaRepository musicRepo;

	@Override
	public Set<Music> findAll() {
		List<Music> listMusic =musicRepo.findAll();
		return new HashSet<Music>(listMusic);
	}


	@Override
	public Optional<Music> findById(Long id) {
		return musicRepo.findById(id);
	}


	@Override
	public Set<Music> findByInterprete(String interprete) {
		return musicRepo.findByInterprete(interprete);
	}


	@Override
	public Set<Music> findByCompositor(String compositor) {
		return musicRepo.findByCompositor(compositor);
	}


	@Override
	public Music addMusic(Music music) {
		return musicRepo.save(music);
	}


	@Override
	public Music modifyMusic(Long id, Music newMusic) {
		Optional<Music> musik =  musicRepo.findById(id);
		//Music musica = musik.get();
//				.orElseThrow(() -> new MusicNotFoundException(id));
		newMusic.setId(musik.get().getId());
		return musicRepo.save(newMusic);
	}


	@Override
	public void deleteMusic(Long id) {
		musicRepo.findById(id)
				.orElseThrow(() -> new MusicNotFoundException());
		musicRepo.deleteById(id);
	}
	
}
