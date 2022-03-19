package com.intuit.business.customerapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.intuit.business.customerapp.domain.CustomerProfile;
import com.intuit.business.customerapp.serializersDeserializers.CustomRedisSerializer;
import com.intuit.business.customerapp.serializersDeserializers.CustomSerializer;

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
