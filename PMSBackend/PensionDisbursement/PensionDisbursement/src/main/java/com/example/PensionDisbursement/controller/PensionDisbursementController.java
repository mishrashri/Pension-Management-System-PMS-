package com.example.PensionDisbursement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.PensionDisbursement.model.ProcessPensionInput;
import com.example.PensionDisbursement.model.ProcessPensionResponse;
import com.example.PensionDisbursement.service.PensionDisbursementService;

@RestController
public class PensionDisbursementController {
	
	@Autowired
	PensionDisbursementService pensionDisbursementService;
	
	Logger LOGGER = LoggerFactory.getLogger(PensionDisbursementController.class);
	
	@PostMapping("/disbursepension")
	public ProcessPensionResponse disbursePension(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader, @RequestBody ProcessPensionInput processPensionInput)
	{
		LOGGER.info("START disbursePension");
		return pensionDisbursementService.disbursePension(requestTokenHeader ,processPensionInput);
	}
}
