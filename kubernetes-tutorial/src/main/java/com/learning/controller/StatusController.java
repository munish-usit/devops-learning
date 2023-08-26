package com.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class StatusController {

	@Value("${server.port}")
	private String serverPort;
	
	@Value("${spring.application.name}")
	private String applicationName;
	
	@Value("${spring.profiles.active}")
	private String profile;
	
	@Value("${status.message}")
	private String stausMessage;
	
	@Value("${eureka.client.serviceUrl.defaultZone}")
	private String eurekaUrl;
	
	@Autowired
	private Environment environment;
	
	
	@GetMapping("/kube/status")
	public ResponseEntity<String> getStatus() throws JsonProcessingException {
		log.info("devops status start serverPort {}, applicationName {}, profile {}, message {}",serverPort,applicationName,profile,stausMessage);
		
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		
		rootNode.put("status", "MONITORING");
		rootNode.put("version", "1.0.0");
	
		rootNode.put("serverPort", serverPort);
		rootNode.put("applicationName", applicationName);
		rootNode.put("profile", profile);
		rootNode.put("stausMessage", stausMessage);
		rootNode.put("eurekaUrl", eurekaUrl);
	
		rootNode.put("SPRING_PROFILES_ACTIVE", environment.getProperty("SPRING_PROFILES_ACTIVE"));
		rootNode.put("Path", environment.getProperty("Path"));
		
		
		String jsonString = mapper.writeValueAsString(rootNode);
		log.info("devops status  {} ",jsonString);
		
		return ResponseEntity.ok(jsonString);	
		
	}
}
