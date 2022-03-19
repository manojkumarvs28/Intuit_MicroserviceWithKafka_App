package com.intuit.business.productapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class RedisConfig {

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName("localhost");
		redisStandaloneConfiguration.setPort(6379);
		//redisStandaloneConfiguration.setPassword("");
		
		// we can set maxpool size, max idle timeout etc can nbe set on JedisConnection factory
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
		return jedisConnectionFactory;
	}
//	
//	@Bean
//	public RedisTemplate<String, String> redisTemplate(){
//		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
//		redisTemplate.setConnectionFactory(jedisConnectionFactory());
//		
////		redisTemplate.setKeySerializer(new StringRedisSerializer());
////		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
////		//redisTemplate.setHashKeySerializer(new JdkSerializationRedisSerializer());
////		redisTemplate.setValueSerializer(new CustomRedisSerializer());
//		
//		redisTemplate.setEnableTransactionSupport(true);
//		redisTemplate.afterPropertiesSet();
//		
//		return redisTemplate;
//	}
}
