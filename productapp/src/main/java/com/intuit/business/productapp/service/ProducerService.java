package com.intuit.business.productapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.intuit.business.productapp.constants.Constants;
import com.intuit.business.productapp.domain.CustomerProfile;

@Service
public class ProducerService {
	private static final String Topic = Constants.PRODUCER_TOPIC;
	
	@Autowired
	private KafkaTemplate<String, CustomerProfile> kafkaTemplate;
	
	public void send(CustomerProfile customerProfile) {
		ListenableFuture<SendResult<String, CustomerProfile>> future = this.kafkaTemplate.send(Topic, customerProfile);
		future.addCallback(new ListenableFutureCallback<SendResult<String, CustomerProfile>>() {
			@Override
			public void onFailure(Throwable ex) {
				System.out.println("Sending message failed with exception "+ ex.getMessage());
			}

			@Override
			public void onSuccess(SendResult<String, CustomerProfile> result) {
				System.out.println("Message Sent "+ customerProfile + " to topic "+ result.getRecordMetadata().topic() + 
						" And offset is "+ result.getRecordMetadata().offset());
			}
		});
		
	}
	
}
