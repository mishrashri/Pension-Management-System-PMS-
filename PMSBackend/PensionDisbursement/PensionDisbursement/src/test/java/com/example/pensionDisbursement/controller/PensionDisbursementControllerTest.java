package com.example.pensionDisbursement.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.PensionDisbursement.controller.PensionDisbursementController;
import com.example.PensionDisbursement.model.ProcessPensionInput;
import com.example.PensionDisbursement.model.ProcessPensionResponse;
import com.example.PensionDisbursement.service.PensionDisbursementService;

@AutoConfigureMockMvc
@SpringBootTest
public class PensionDisbursementControllerTest {
	
	@InjectMocks
	PensionDisbursementController pensionDisbursementController;
	
	@Autowired
	MockMvc mockMvc;
	
	@Mock
	PensionDisbursementService pensionDisbursementService;
	
	@Test
	public void contextLoad()
	{
		assertNotNull(pensionDisbursementController);
	}
	
	@Test
	public void testDisbursePension()
	{
		ProcessPensionResponse pensionResponse = new ProcessPensionResponse();
		pensionResponse.setResponse(10);
		
		ProcessPensionInput pensionInput = new ProcessPensionInput();
		pensionInput.setAadhaar(1234567890123l);
		pensionInput.setPensionAmount(22143);
		pensionInput.setBankServiceCharge(500);
		when(pensionDisbursementService.disbursePension("token", pensionInput)).thenReturn(pensionResponse);
		assertNotNull(pensionDisbursementController.disbursePension("token", pensionInput));
	}
}
