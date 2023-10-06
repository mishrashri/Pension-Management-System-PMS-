package com.example.processPension.service;

import java.sql.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.processPension.client.AuthClient;
import com.example.processPension.client.PensionDisbursementClient;
import com.example.processPension.client.PensionerDetailClient;
import com.example.processPension.entity.PensionDetail;
import com.example.processPension.exception.PensionerNotFoundException;
import com.example.processPension.exception.TokenExpireException;
import com.example.processPension.model.PensionerDetail;
import com.example.processPension.model.ProcessPensionInput;
import com.example.processPension.model.ProcessPensionResponse;
import com.example.processPension.reository.ProcessPensionRepository;
import jakarta.transaction.Transactional;

@Service
public class ProcessPensionService {
	
	@Autowired
	PensionerDetailClient pensionerDetailClient;
	@Autowired
	PensionDisbursementClient pensionDisbursementClient;
	@Autowired
	AuthClient authClient;
	@Autowired
	ProcessPensionRepository pensionRepository;
	
	PensionDetail pensionDetail = new PensionDetail(); 
	
	Logger LOGGER = LoggerFactory.getLogger(ProcessPensionService.class);
	
	@Transactional
	public PensionDetail pensionDetail(String requestTokenHeader, String name, Date dob, String pan, long aadhaar, String pensionType) throws PensionerNotFoundException
	{	
		LOGGER.info("START pensionDetail");
		PensionerDetail pensionerDetail = pensionerDetailClient.pensionerDetailByAadhaar(requestTokenHeader, aadhaar);
		float pensionAmount = 0.0f;
		
			if(pensionerDetail.getName().equalsIgnoreCase(name) && dob.toString().equals(pensionerDetail.getDob().toString()) && pensionerDetail.getPan().equalsIgnoreCase(pan) && pensionerDetail.getAadhaar() == aadhaar && pensionerDetail.getPensionType().equalsIgnoreCase(pensionType))
			{
				if(pensionType.equalsIgnoreCase("self"))
				{
					pensionAmount = (pensionerDetail.getLastSalary() * 80/100)+ pensionerDetail.getAllowance() ;
					  
				}
				else
				{
					pensionAmount = (pensionerDetail.getLastSalary() * 50/100)+ pensionerDetail.getAllowance() ;
				}
				 pensionDetail.setName(name);
				 pensionDetail.setPan(pan);
				 pensionDetail.setDob(dob);
				 pensionDetail.setPensionType(pensionType); 
				 pensionDetail.setPensionAmount(pensionAmount);
				 LOGGER.info("END pensionDetail");
				 return pensionRepository.save(pensionDetail);
				  
			}
			else
			{
				LOGGER.info("END pensionDetail");
				throw new PensionerNotFoundException("Invalid pensioner detail provided, please provide valid detail.");
			}
		
	}
	
	@Transactional
	public ProcessPensionResponse pensionProcess(String requestTokenHeader, ProcessPensionInput pensionInput) throws TokenExpireException
	{
		LOGGER.info("START pensionProcess");
		if(authClient.authorizeTheRequest(requestTokenHeader))
		{
			ProcessPensionResponse pensionResponse = pensionDisbursementClient.disbursePension(requestTokenHeader, pensionInput);
			LOGGER.info("END pensionProcess");
			return pensionResponse;
		}
		else
		{
			LOGGER.info("END pensionProcess");
			throw new TokenExpireException("Token is not valid");
		}
	}
}
