//
//package com.sns.pjt.redis;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import redis.clients.jedis.JedisPoolConfig;
//
//@Configuration
//
//@PropertySource("classpath:application.properties")
//public class RedisConfig {
//
//	private @Value("${spring.redis.host}") String redisHost;
//	private @Value("${spring.redis.port}") int redisPort;
//
//	@Bean
//	public JedisPoolConfig jedisPoolConfig() {
//		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//		return jedisPoolConfig;
//	}
//
//	@Bean
//	public JedisConnectionFactory jedisConnectionFactory() {
//		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost,
//				redisPort);
//
//		return new JedisConnectionFactory(redisStandaloneConfiguration);
//	}
//
//	@Bean(name = "redisTemplate")
//	public RedisTemplate redisTemplateConfig(JedisConnectionFactory jedisConnectionFactory) {
//		RedisTemplate redisTemplate = new RedisTemplate();
//
//		redisTemplate.setKeySerializer(new StringRedisSerializer());
//		redisTemplate.setValueSerializer(new StringRedisSerializer());
//		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//		redisTemplate.setHashValueSerializer(new StringRedisSerializer());
//		redisTemplate.setConnectionFactory(jedisConnectionFactory);
//
//		return redisTemplate;
//	}
//
//}
