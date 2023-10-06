package com.example.processPension.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.processPension.model.ProcessPensionInput;
import com.example.processPension.model.ProcessPensionResponse;


@FeignClient(name = "PensionDisbursement", url = "http://localhost:8082")
public interface PensionDisbursementClient {
	
	@PostMapping("/disbursepension")
	public ProcessPensionResponse disbursePension(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader, @RequestBody ProcessPensionInput processPensionInput);
}
