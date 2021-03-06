package com.intuit.business.productapp.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.intuit.business.productapp.domain.CustomerProfile;
//import com.intuit.business.customerapp.serializersDeserializers.CustomSerializer;

@Configuration
public class KafkaProducerConfig {

	@Bean
	public ProducerFactory<String, CustomerProfile> producerFactory() {
		Map<String, Object> configs = new HashMap<String, Object>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<String, CustomerProfile>(configs);
	}
	
	@Bean
	public KafkaTemplate<String, CustomerProfile> kafkatemplate() {
		return new KafkaTemplate<String, CustomerProfile>(producerFactory());
	}
	
}
