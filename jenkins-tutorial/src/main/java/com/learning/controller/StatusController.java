package com.learning.controller;

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

	@GetMapping("/jenkins/status")
	public ResponseEntity<String> getStatus() throws JsonProcessingException {
		log.info("jenkins status start");
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		ObjectNode childNode = mapper.createObjectNode();
		childNode.put("status", "MONITORING");
		childNode.put("version", "1.0.0");
		childNode.put("purpose", "DEV");
		rootNode.set("health", childNode);
		
		String jsonString = mapper.writeValueAsString(rootNode);
		log.info("jenkins status  {} ",jsonString);
		
		return ResponseEntity.ok(jsonString);	
		
	}
}
