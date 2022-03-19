package com.intuit.business.customerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.intuit.business.customerapp.constants.Constants;
import com.intuit.business.customerapp.domain.CustomerProfile;
import com.intuit.business.customerapp.events.ValidationEvent;

@Service
public class ConsumerService {
	
	@Autowired
	ApplicationEventPublisher publisher;
	
	private final Gson gson = new Gson();
	
	@KafkaListener(topics= Constants.CONSUMER_TOPIC, groupId= Constants.CONSUMER_GROUP_ID, containerFactory = "kafkaListerenerFactory2")
	public void receivedMessage(String customerProfile) {
		System.out.println("Recieved message in our class ");
		CustomerProfile profile = gson.fromJson(customerProfile, CustomerProfile.class);
		publisher.publishEvent(new ValidationEvent(this, profile));
	}
	
}
