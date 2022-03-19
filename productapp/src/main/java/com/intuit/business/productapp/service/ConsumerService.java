package com.intuit.business.productapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.intuit.business.productapp.constants.Constants;
import com.intuit.business.productapp.domain.CustomerProfile;
import com.intuit.business.productapp.events.ValidationEvent;

@Service
public class ConsumerService {
	
	@Autowired
	ApplicationEventPublisher publisher;
	
	private final Gson gson = new Gson();
	
	@KafkaListener(topics= Constants.CONSUMER_TOPIC, groupId=  Constants.CONSUMER_GROUP_ID, containerFactory = "kafkaListerenerFactory2")
	public void receivedMessage(String customerProfile) {
		CustomerProfile profile = gson.fromJson(customerProfile, CustomerProfile.class);
		System.out.println("Received message in our class ");
		publisher.publishEvent(new ValidationEvent(this, profile));
	}
	
}
