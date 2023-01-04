package com.javero.redispring.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumerapi")
public class RedisController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	
										// operaciones de REDIS
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	private final String BASE_URL = "https://jsonplaceholder.typicode.com/posts/";
	
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id){
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			ValueOperations<String, String> valueOp = redisTemplate.opsForValue();
			
			String data = valueOp.get(getId(id.toString()));
			if(data != null && !data.isEmpty()) {
				return new ResponseEntity<String>(data, headers, HttpStatus.OK);
			}
			
			ResponseEntity<String> response = restTemplate.exchange(BASE_URL.concat(id.toString()), HttpMethod.GET, null, String.class);

			if(response.getStatusCodeValue() == 200) {
				valueOp.set(getId(id.toString()), response.getBody(), Duration.ofSeconds(200));
			}
			return new ResponseEntity<String>(response.getBody(), headers, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private String getId(String id) {
		return "Picode".concat(id);
	}
	
	

}
