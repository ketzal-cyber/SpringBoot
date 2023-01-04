package com.javero.redispring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfig {

	@Bean
	RedisStandaloneConfiguration redisconfig() {
		RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
		redisConfig.setHostName("127.0.0.1");
		redisConfig.setPort(6380);
		redisConfig.setPassword("javero1222");
		return redisConfig;
	}
	
	@Bean
	LettuceConnectionFactory redisConnectionFactory() {
		//LettuceConnectionFactory lettuceConnFact = new LettuceConnectionFactory();
		//lettuceConnFact.getStandaloneConfiguration();
		//return lettuceConnFact;
		return new LettuceConnectionFactory(redisconfig());
	}
	
	@Bean
	StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}
	
}
