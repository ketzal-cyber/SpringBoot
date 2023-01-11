package com.javero.redispring.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javero.redispring.entity.Music;

@Repository
public interface MusicaRepository extends JpaRepository<Music, Long> {

	Set<Music> findByCompositor(String compositor); 
	Set<Music> findByInterprete(String interprete);
}
