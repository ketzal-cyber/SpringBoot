package com.javero.redispring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import io.lettuce.core.RedisURI;

@Configuration
public class RedisConfig {

	//Configuracion de coneccion autonoma
	@Bean
	RedisStandaloneConfiguration redisconfig() {
		RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
		redisConfig.setHostName("172.30.236.108");
		redisConfig.setPort(6379);
		//redisConfig.setPassword("javero1222");
		return redisConfig;
	}
	
	@Bean
	LettuceConnectionFactory redisConnectionFactory() {
		
		return new LettuceConnectionFactory(redisconfig());
	}
	
	@Bean
	GenericJackson2JsonRedisSerializer getGenericJackson2jsonRedisSerializer() {
		return new GenericJackson2JsonRedisSerializer();
	}
	
	@Bean
	RedisTemplate<String, String> getRedisTemplate(LettuceConnectionFactory lettuceConnection){
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(lettuceConnection);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setValueSerializer(getGenericJackson2jsonRedisSerializer());
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}
	
	
	
	
	
	
}
