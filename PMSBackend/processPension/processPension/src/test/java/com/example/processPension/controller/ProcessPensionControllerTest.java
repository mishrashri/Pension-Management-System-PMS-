package com.example.processPension.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import java.sql.Date;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.processPension.client.AuthClient;
import com.example.processPension.entity.PensionDetail;
import com.example.processPension.exception.PensionerNotFoundException;
import com.example.processPension.exception.TokenExpireException;
import com.example.processPension.model.ProcessPensionInput;
import com.example.processPension.model.ProcessPensionResponse;
import com.example.processPension.service.ProcessPensionService;

@SpringBootTest
@AutoConfigureMockMvc
public class ProcessPensionControllerTest{

	@InjectMocks
	ProcessPensionController processPensionController;// This we are testing thats why it is inject mocked
	
	@Mock
	AuthClient auth;
	
	@Mock
	ProcessPensionService processPensionService;
	
	@Autowired
	MockMvc mockMvc;
	
	Date date=new Date(06-03-2001);
	
	@Test
	public void contextLoad() {
		assertNotNull(processPensionService);
	}
	
	@Test
	public void testPensionDetail() throws PensionerNotFoundException
	{
		PensionDetail pensionDetail = new PensionDetail();
		pensionDetail.setDob(date);
		pensionDetail.setName("narayan mishra");
		pensionDetail.setPan("FMNPM1234Q");
		when(processPensionService.pensionDetail("token", "narayan mishra",date, "FMNPM1234Q", 120033441200l, "self")).thenReturn(pensionDetail);
		assertNotNull(processPensionController.pensionDetail("token", "narayan mishra",date, "FMNPM1234Q", "self", 120033441200l));
	}
	
	@Test
	public void testProcessPension() throws TokenExpireException {
		ProcessPensionInput processPensionInput=new ProcessPensionInput();
		ProcessPensionResponse processPensionResponse=new ProcessPensionResponse();
		processPensionResponse.setResponse(10);
		processPensionInput.setAadhaar(120033441200l);
		processPensionInput.setBankServiceCharge(500);
		processPensionInput.setPensionAmount(23000.0f);
		when(processPensionService.pensionProcess("token",processPensionInput)).thenReturn(processPensionResponse);
		assertNotNull(processPensionController.processPension("token", processPensionInput));
	}
	
}
