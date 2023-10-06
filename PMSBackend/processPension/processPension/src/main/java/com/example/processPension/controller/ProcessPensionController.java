package com.example.processPension.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.processPension.entity.PensionDetail;
import com.example.processPension.exception.PensionerNotFoundException;
import com.example.processPension.exception.TokenExpireException;
import com.example.processPension.model.ProcessPensionInput;
import com.example.processPension.model.ProcessPensionResponse;
import com.example.processPension.service.ProcessPensionService;

@CrossOrigin
@RestController
public class ProcessPensionController {
	
	@Autowired
	ProcessPensionService processPensionService;
	
	Logger LOGGER = LoggerFactory.getLogger(ProcessPensionController.class);
	
	@GetMapping("/pensiondetail")
	public PensionDetail pensionDetail(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader, @RequestParam("name") String name, @RequestParam("dob") java.sql.Date dob, @RequestParam("pan") String pan, @RequestParam("pensiontype") String pensionType, @RequestParam("aadhaar") long aadhaar) throws PensionerNotFoundException
	{
		LOGGER.info("START pensionDetail");
		return processPensionService.pensionDetail(requestTokenHeader, name, dob, pan, aadhaar, pensionType);
	}
	
	@PostMapping("/processpension")
	public ProcessPensionResponse processPension(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader, @RequestBody ProcessPensionInput pensionInput) throws TokenExpireException
	{
		LOGGER.info("START processPension");
		return processPensionService.pensionProcess(requestTokenHeader, pensionInput);
	}
}
