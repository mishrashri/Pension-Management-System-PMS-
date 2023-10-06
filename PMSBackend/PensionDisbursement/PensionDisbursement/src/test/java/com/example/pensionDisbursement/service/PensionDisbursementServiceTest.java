package com.example.pensionDisbursement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.PensionDisbursement.client.AuthClient;
import com.example.PensionDisbursement.client.PensionDetailClient;
import com.example.PensionDisbursement.model.PensionerDetail;
import com.example.PensionDisbursement.model.ProcessPensionInput;
import com.example.PensionDisbursement.model.ProcessPensionResponse;
import com.example.PensionDisbursement.service.PensionDisbursementService;

@AutoConfigureMockMvc
@SpringBootTest
public class PensionDisbursementServiceTest {
	
	@InjectMocks
	PensionDisbursementService pensionDisbursementService;
	
	@Mock
	PensionDetailClient pensionDetailClient;
	
	@Autowired
	AuthClient authClient;
	
	@Test
	public void testDisbursePensionFamilyPublic()
	{
		ProcessPensionInput processPensionInput = new ProcessPensionInput();
		processPensionInput.setAadhaar(1234567890123l);
		processPensionInput.setBankServiceCharge(500);
		processPensionInput.setPensionAmount(21233.5f);
		PensionerDetail pensionerDetail = new PensionerDetail();
		pensionerDetail.setAadhaar(1234567890123l);
		pensionerDetail.setAcccountNo(987654321);
		pensionerDetail.setBankType("public");
		pensionerDetail.setLastSalary(39999);
		pensionerDetail.setPensionType("family");
		pensionerDetail.setAllowance(1234);
		when(pensionDetailClient.pensionerDetailByAadhaar("token", 1234567890123l)).thenReturn(pensionerDetail);
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
		float pensionAmount = 0.0f;
		float bankCharge = 0.0f;
		pensionAmount = (pensionerDetail.getLastSalary() * 50/100) + pensionerDetail.getAllowance();
		
		
		assertTrue(pensionerDetail.getBankType().equalsIgnoreCase("public"));
		bankCharge = 500;
		
		assertTrue(processPensionInput.getPensionAmount() == pensionAmount && processPensionInput.getBankServiceCharge() == bankCharge);
		processPensionResponse.setResponse(10);
		
		assertNotNull(pensionDisbursementService.disbursePension("token", processPensionInput));
		assertEquals(processPensionResponse.getResponse(),10);
	}
	
	@Test
	public void testDisbursePensionFamilyPrivate()
	{
		ProcessPensionInput processPensionInput = new ProcessPensionInput();
		processPensionInput.setAadhaar(1234567890123l);
		processPensionInput.setBankServiceCharge(550);
		processPensionInput.setPensionAmount(33233.2f);
		PensionerDetail pensionerDetail = new PensionerDetail();
		pensionerDetail.setAadhaar(1234567890123l);
		pensionerDetail.setAcccountNo(987654321);
		pensionerDetail.setBankType("private");
		pensionerDetail.setLastSalary(39999);
		pensionerDetail.setPensionType("family");
		pensionerDetail.setAllowance(1234);
		when(pensionDetailClient.pensionerDetailByAadhaar("token", 1234567890123l)).thenReturn(pensionerDetail);
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
		float pensionAmount = 0.0f;
		float bankCharge = 0.0f;
		pensionAmount = (pensionerDetail.getLastSalary() * 80/100) + pensionerDetail.getAllowance();
	
		assertTrue(pensionerDetail.getBankType().equalsIgnoreCase("private"));
		bankCharge = 550;
		
		assertTrue(processPensionInput.getPensionAmount() == pensionAmount && processPensionInput.getBankServiceCharge() == bankCharge);
		processPensionResponse.setResponse(10);
		
		assertNotNull(pensionDisbursementService.disbursePension("token", processPensionInput));
		assertEquals(processPensionResponse.getResponse(),10);
	}
	
	@Test
	public void testDisbursePensionSelf()
	{
		ProcessPensionInput processPensionInput = new ProcessPensionInput();
		processPensionInput.setAadhaar(12345678901l);
		processPensionInput.setBankServiceCharge(500);
		processPensionInput.setPensionAmount(33233.2f);
		PensionerDetail pensionerDetail = new PensionerDetail();
		pensionerDetail.setAadhaar(12345678901l);
		pensionerDetail.setAcccountNo(987654);
		pensionerDetail.setBankType("public");
		pensionerDetail.setLastSalary(39999);
		pensionerDetail.setPensionType("self");
		pensionerDetail.setAllowance(1234);
		when(pensionDetailClient.pensionerDetailByAadhaar("token", 12345678901l)).thenReturn(pensionerDetail);
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
		float pensionAmount = 0.0f;
		float bankCharge = 0.0f;
		pensionAmount = (pensionerDetail.getLastSalary() * 80/100) + pensionerDetail.getAllowance();
				
		assertTrue(pensionerDetail.getBankType().equalsIgnoreCase("public"));
		bankCharge = 500;
		
		assertTrue(processPensionInput.getPensionAmount() == pensionAmount && processPensionInput.getBankServiceCharge() == bankCharge);
		processPensionResponse.setResponse(10);
		
		assertNotNull(pensionDisbursementService.disbursePension("token", processPensionInput));
		assertEquals(processPensionResponse.getResponse(),10);
	}
	
	@Test
	public void testDisbursePensionFail()
	{
		ProcessPensionInput processPensionInput = new ProcessPensionInput();
		processPensionInput.setAadhaar(1234567890123l);
		processPensionInput.setBankServiceCharge(1000);
		processPensionInput.setPensionAmount(21233.5f);
		PensionerDetail pensionerDetail = new PensionerDetail();
		pensionerDetail.setAadhaar(1234567890123l);
		pensionerDetail.setAcccountNo(987654321);
		pensionerDetail.setBankType("private");
		pensionerDetail.setLastSalary(39999);
		pensionerDetail.setPensionType("family");
		pensionerDetail.setAllowance(1234);
		when(pensionDetailClient.pensionerDetailByAadhaar("token", 1234567890123l)).thenReturn(pensionerDetail);
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
		float pensionAmount = 0.0f;
		float bankCharge = 0.0f;
		
		pensionAmount = (pensionerDetail.getLastSalary() * 50/100) + pensionerDetail.getAllowance();
		
		assertTrue(pensionerDetail.getBankType().equalsIgnoreCase("private"));
		bankCharge = 550;

		assertFalse(processPensionInput.getPensionAmount() == pensionAmount && processPensionInput.getBankServiceCharge() == bankCharge);
		processPensionResponse.setResponse(21);
		
		assertNotNull(pensionDisbursementService.disbursePension("token", processPensionInput));
		assertEquals(processPensionResponse.getResponse(),21);
	}
	
}
