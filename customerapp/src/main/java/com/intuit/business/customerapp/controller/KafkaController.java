package com.intuit.business.customerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.intuit.business.customerapp.config.Producer;

@RestController
@RequestMapping
public class KafkaController {
 
//	@Autowired
//	Producer producer;
	
	
	@PostMapping("/kafkaPost")
	public void sendMessage(@RequestParam String msg) {
		//producer.publishToTopic(msg);
	}
	
}
