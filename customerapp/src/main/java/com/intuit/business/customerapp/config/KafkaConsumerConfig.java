package com.intuit.business.customerapp.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.intuit.business.customerapp.constants.Constants;
import com.intuit.business.customerapp.domain.CustomerProfile;
import com.intuit.business.customerapp.serializersDeserializers.CustomDeserializer;

@Configuration
public class KafkaConsumerConfig {

//	@Bean
//	public ConsumerFactory<String, CustomerProfile> consumerfactory() {
//		Map<String, Object> configs = new HashMap<String, Object> ();
//		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
//		//return new DefaultKafkaConsumerFactory<String, CustomerProfile>(configs);
//	    return new DefaultKafkaConsumerFactory<>(
//	    		configs, new StringDeserializer(),
//	            new JsonDeserializer<>(CustomerProfile.class));
//	}
//	
//	@Bean
//	public ConcurrentKafkaListenerContainerFactory<String, CustomerProfile> kafkaListerenerFactory() {
//		ConcurrentKafkaListenerContainerFactory<String, CustomerProfile> factory = new ConcurrentKafkaListenerContainerFactory<>();
//		factory.setConsumerFactory(consumerfactory());
//		return factory;
// 	}
	
	@Bean
	public ConsumerFactory<String, String> consumerfactory2() {
		Map<String, Object> configs = new HashMap<String, Object> ();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, Constants.CONSUMER_GROUP_ID);
		//return new DefaultKafkaConsumerFactory<String, CustomerProfile>(configs);
	    return new DefaultKafkaConsumerFactory<>(
	    		configs, new StringDeserializer(),
	            new StringDeserializer());
	}
	
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListerenerFactory2() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerfactory2());
		return factory;
 	}
	
}
