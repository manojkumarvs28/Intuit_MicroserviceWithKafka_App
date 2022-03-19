package com.intuit.business.productapp.utilities;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.intuit.business.productapp.constants.ErrorMessageConstants;

@Service
public class RedisUtility {
	
	private static Logger logger =  LoggerFactory.getLogger(RedisUtility.class);

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	public void addToCache(String key, String id, String jsonValue) {
		try {
			redisTemplate.opsForHash().put(key, id , jsonValue);
		}catch(Exception ex) {
			logger.error(ErrorMessageConstants.ERROR_OCCURED_WHILE_STORING_DATA_TO_CACHE, ex.getMessage());
		}
	}
	
	public Object getCachedValue(String key, String id) {
		Object object= null;
		try {
			object= redisTemplate.opsForHash().get(key, id);
		}catch(Exception ex) {
			logger.error(ErrorMessageConstants.ERROR_OCCURED_WHILE_RETRIEVING_DATA_TO_CACHE, ex.getMessage());
		}
		return object;
	}
	
	public void deleteCachedValue(String key, String id) {
		try {
			redisTemplate.opsForHash().delete(key, id);
		}catch(Exception ex) {
			logger.error(ErrorMessageConstants.ERROR_OCCURED_WHILE_DELETING_DATA_FROM_CACHE, ex.getMessage());
		}
	}
	
	public List<Object> getAllCachedValues(String key){
		List<Object> list= null;
		try {
			list = redisTemplate.opsForHash().values(key);
		}catch(Exception ex) {
			logger.error(ErrorMessageConstants.ERROR_OCCURRED_WHILE_RETRIEVING_ALL_DATA_FROM_CACHE, ex.getMessage());
		}
		return list;
	}
}
