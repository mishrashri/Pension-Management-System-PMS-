package com.example.pensionerDetail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pensionerDetail.entity.PensionerDetail;
import com.example.pensionerDetail.exception.AadhaarNotFoundException;
import com.example.pensionerDetail.exception.TokenExpireException;
import com.example.pensionerDetail.service.PensionerDetailService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PensionerDetailController {
	
	@Autowired
	PensionerDetailService pensionerDetailService;
	
	Logger LOGGER = LoggerFactory.getLogger(PensionerDetailController.class);
	
	@GetMapping("/pensionerdetailbyaadhaar")
	public PensionerDetail pensionerDetailByAadhaar(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader, @RequestParam("aadhaar") long aadhaar) throws AadhaarNotFoundException, TokenExpireException
	{
		LOGGER.info("START pensionerDetailByAadhaar");
		return pensionerDetailService.pensionerDetailByAadhaar(requestTokenHeader,aadhaar);
	}
}
