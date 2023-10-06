package com.example.pensionerDetail.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.pensionerDetail.model.JwtRequest;

//@FeignClient(name = "${auth.name}", url = "${auth.url}" )
@FeignClient(name = "Authorizatiion-Microservice", url = "http://localhost:8400/auth")
public interface AuthClient {
	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest);
	
	@PostMapping(value = "/authorize")
	public boolean authorizeTheRequest(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader);

}
