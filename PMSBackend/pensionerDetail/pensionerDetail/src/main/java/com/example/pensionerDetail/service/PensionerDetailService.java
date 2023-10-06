package com.example.pensionerDetail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.pensionerDetail.client.AuthClient;
import com.example.pensionerDetail.entity.PensionerDetail;
import com.example.pensionerDetail.exception.AadhaarNotFoundException;
import com.example.pensionerDetail.exception.TokenExpireException;
import com.example.pensionerDetail.repository.PensionerDetailRepository;

import jakarta.transaction.Transactional;

@Service
public class PensionerDetailService {
	
	@Autowired
	PensionerDetailRepository pensionerDetailRepository;
	@Autowired
	AuthClient authClient;
	
	Logger LOGGER = LoggerFactory.getLogger(PensionerDetailService.class);
	
	@Transactional
	public PensionerDetail pensionerDetailByAadhaar(String requestTokenHeader,long aadhaar) throws AadhaarNotFoundException, TokenExpireException
	{
		LOGGER.info("START pensionerDetailByAadhaar");
		if(authClient.authorizeTheRequest(requestTokenHeader))
		{
			PensionerDetail pensioner = pensionerDetailRepository.getPensionerDetail(aadhaar);
			
			if(pensioner != null)
			{
				LOGGER.info("END pensionerDetailByAadhaar");
				return pensioner;
			}
			else
			{
				LOGGER.info("END pensionerDetailByAadhaar");
				throw new AadhaarNotFoundException("Invalid pensioner detail provided, please provide valid detail.");
			}
		}
		else
		{
			LOGGER.info("END pensionerDetailByAadhaar");
			throw new TokenExpireException("Token expired");
		}
	}
	
}
