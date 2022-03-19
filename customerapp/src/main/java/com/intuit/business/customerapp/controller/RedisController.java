package com.intuit.business.customerapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class RedisController {
	
	String Key = "Custom";
	
	@Autowired
	RedisTemplate<String, String> restTemplate;
	
	@GetMapping("/redis")
	public List<Object> getVal(@RequestParam String key) {
		try {
			String string = (String) restTemplate.opsForHash().get(Key, "abc");
			return  restTemplate.opsForHash().values(Key);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("/redis")
	public void putVal(@RequestParam String val) {
		try {
		 restTemplate.opsForHash().put(Key, "abc", val);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@DeleteMapping("/redis")
	public void delete(@RequestParam String key) {
		try {
			  restTemplate.opsForHash().delete(Key, key);
			}catch(Exception ex) {
				ex.printStackTrace();
		}
	}
	
}
