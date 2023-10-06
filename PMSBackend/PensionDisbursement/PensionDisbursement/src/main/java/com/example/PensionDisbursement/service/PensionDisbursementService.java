package com.example.PensionDisbursement.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PensionDisbursement.client.PensionDetailClient;
import com.example.PensionDisbursement.model.PensionerDetail;
import com.example.PensionDisbursement.model.ProcessPensionInput;
import com.example.PensionDisbursement.model.ProcessPensionResponse;

@Service
public class PensionDisbursementService {
	
	@Autowired
	PensionDetailClient pensionDetailClient;
	
	Logger LOGGER = LoggerFactory.getLogger(PensionDisbursementService.class);
	
	public ProcessPensionResponse disbursePension(String requestTokenHeader, ProcessPensionInput processPensionInput)
	{
		LOGGER.info("START disbursePension");
		PensionerDetail pensionerDetail = pensionDetailClient.pensionerDetailByAadhaar(requestTokenHeader, processPensionInput.getAadhaar());
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
		float pensionAmount = 0.0f;
		float bankCharge = 0.0f;
		pensionAmount = pensionerDetail.getPensionType().equalsIgnoreCase("self") ?
				(pensionerDetail.getLastSalary() * 80/100) + pensionerDetail.getAllowance():
					(pensionerDetail.getLastSalary() * 50/100) + pensionerDetail.getAllowance();
			
		bankCharge = pensionerDetail.getBankType().equalsIgnoreCase("private") ? 550 : 500;
				
		if(processPensionInput.getPensionAmount() == pensionAmount && processPensionInput.getBankServiceCharge() == bankCharge)
			processPensionResponse.setResponse(10);
		else
			processPensionResponse.setResponse(21); 
		LOGGER.info("END disbursePension");
		return processPensionResponse;
			
	}
}
