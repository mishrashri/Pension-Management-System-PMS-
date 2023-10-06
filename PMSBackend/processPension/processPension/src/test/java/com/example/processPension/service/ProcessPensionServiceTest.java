package com.example.processPension.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.text.ParseException;



import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

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

@AutoConfigureMockMvc
@SpringBootTest
public class ProcessPensionServiceTest { 

	@InjectMocks
	ProcessPensionService processPensionService;

	@Mock
	PensionerDetailClient pensionerDetailClient;

	@Mock
	ProcessPensionRepository processPensionRepository;

	@Mock
	PensionDisbursementClient pensionDisbursementClient;

	@Mock
	AuthClient authClient;

	@Test
	public void testPensionDetailFamily() throws PensionerNotFoundException, ParseException {
		String name = "Harsh";
		Date dob = new Date(1999-8-5);
//		Date dob = new Date();
		String pan = "AUIPH1684D";
		long aadhaar = 1234567890123l;
		String pensionType1 = "family";

		PensionerDetail pensionerDetail1 = new PensionerDetail();
		pensionerDetail1.setAadhaar(1234567890123l);
		pensionerDetail1.setName("Harsh");
		pensionerDetail1.setPan("AUIPH1684D");
		pensionerDetail1.setPensionType("family");
		pensionerDetail1.setDob(dob);
		Date  dbDob = new Date(pensionerDetail1.getDob().getTime());

		when(authClient.authorizeTheRequest("token")).thenReturn(true);
		when(pensionerDetailClient.pensionerDetailByAadhaar("token", 1234567890123l)).thenReturn(pensionerDetail1);
		assertTrue(pensionerDetail1.getName().equalsIgnoreCase(name) && dob.toString().equals(dbDob.toString()) && pensionerDetail1.getPan().equalsIgnoreCase(pan)
				&& pensionerDetail1.getAadhaar() == aadhaar
				&& pensionerDetail1.getPensionType().equalsIgnoreCase(pensionType1));
//		float pensionAmount1 = 220.0f;

		PensionDetail pensionDetail1 = new PensionDetail();
		pensionDetail1.setName(name);
		pensionDetail1.setPan(pan);
		pensionDetail1.setDob(dob);
		pensionDetail1.setPensionType(pensionType1);
		
		float pensionAmount1 = 0.0f;
		
			pensionAmount1 = (pensionType1.equalsIgnoreCase("self")) ? (pensionerDetail1.getLastSalary() * 80/100)+ pensionerDetail1.getAllowance() : (pensionerDetail1.getLastSalary() * 50/100)+ pensionerDetail1.getAllowance();
			 
		PensionDetail pensionDetail = new PensionDetail();
		pensionDetail.setName(name);
		pensionDetail.setPan(pan);
		pensionDetail.setDob(dob);
		pensionDetail.setPensionType(pensionType1);
		pensionDetail1.setPensionAmount(pensionAmount1);
		
		when(processPensionRepository.save(pensionDetail1)).thenReturn(pensionDetail1);
		assertNotNull(processPensionService.pensionDetail("token", name, dob, pan, aadhaar, pensionType1));
	}

	@Test
	public void testPensionDetailSelf() throws PensionerNotFoundException, ParseException {
		String name = "Harsh";
		Date dob = new Date(1999-8-5);
//		Date dob = new SimpleDateFormat("yyyy-MM-dd").parse("1999-05-08");
		String pan = "AUIPH1684D";
		long aadhaar = 1234567890123l;
		String pensionType = "self";

		PensionerDetail pensionerDetail = new PensionerDetail();
		pensionerDetail.setAadhaar(1234567890123l);
		pensionerDetail.setName("Harsh");
		pensionerDetail.setPan("AUIPH1684D");
		pensionerDetail.setPensionType("self");
		pensionerDetail.setDob(dob);
		Date  dbDob = new Date(pensionerDetail.getDob().getTime());

		when(authClient.authorizeTheRequest("token")).thenReturn(true);
		when(pensionerDetailClient.pensionerDetailByAadhaar("token", 1234567890123l)).thenReturn(pensionerDetail);

		float pensionAmount = 220.0f;

		assertTrue(pensionerDetail.getName().equalsIgnoreCase(name) && dob.toString().equals(dbDob.toString()) && pensionerDetail.getPan().equalsIgnoreCase(pan)
				&& pensionerDetail.getAadhaar() == aadhaar
				&& pensionerDetail.getPensionType().equalsIgnoreCase(pensionType));

		pensionAmount = (pensionType.equalsIgnoreCase("self")) ? (pensionerDetail.getLastSalary() * 80/100)+ pensionerDetail.getAllowance() : (pensionerDetail.getLastSalary() * 50/100)+ pensionerDetail.getAllowance();
			
		PensionDetail pensionDetail = new PensionDetail();
		pensionDetail.setName(name);
		pensionDetail.setPan(pan);
		pensionDetail.setDob(dob);
		pensionDetail.setPensionType(pensionType);
		pensionDetail.setPensionAmount(pensionAmount);

		
		when(processPensionRepository.save(pensionDetail)).thenReturn(pensionDetail);
		assertNotNull(processPensionService.pensionDetail("token", name, dob, pan, aadhaar, pensionType));
	}
	
	@Test
	public void testPensionDetailPensionerNotFoundException() throws ParseException, PensionerNotFoundException
	{
		String name = "Harsh";
		Date dob = new Date(1999-8-5);
//		Date dob = new SimpleDateFormat("yyyy-MM-dd").parse("1999-05-08");
		String pan = "AUIPH1684D"; 
		long aadhaar = 1234567890123l;
		String pensionType = "self";

		PensionerDetail pensionerDetail = new PensionerDetail();
		pensionerDetail.setAadhaar(1234567890123l);
		pensionerDetail.setName("Harsh");
		pensionerDetail.setPan("AUIPH1684D");
		pensionerDetail.setPensionType("family");
		pensionerDetail.setDob(dob);
		Date  dbDob = new Date(pensionerDetail.getDob().getTime());
		when(pensionerDetailClient.pensionerDetailByAadhaar("token", 1234567890123l)).thenReturn(pensionerDetail);
		
		assertFalse(pensionerDetail.getName().equalsIgnoreCase(name) && dob.toString().equals(dbDob.toString()) && pensionerDetail.getPan().equalsIgnoreCase(pan)
				&& pensionerDetail.getAadhaar() == aadhaar
				&& pensionerDetail.getPensionType().equalsIgnoreCase(pensionType));
		
		
		assertThrows(PensionerNotFoundException.class, ()->processPensionService.pensionDetail("token","abc", dob, pan, aadhaar, pensionType));
		
	}
	
	@Test
	public void testPensionProcessSuccess() throws TokenExpireException
	{
		
		ProcessPensionInput processPensionInput = new ProcessPensionInput();
		processPensionInput.setAadhaar(1234567890123l);
		processPensionInput.setBankServiceCharge(500);
		processPensionInput.setPensionAmount(22350.0f);
		
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
		processPensionResponse.setResponse(10);
		when(authClient.authorizeTheRequest("token")).thenReturn(true);
		when(pensionDisbursementClient.disbursePension("token", processPensionInput)).thenReturn(processPensionResponse);
		assertNotNull(processPensionService.pensionProcess("token", processPensionInput));
	}
	@Test
	public void testPensionProcessFail() throws TokenExpireException
	{
		
		ProcessPensionInput processPensionInput = new ProcessPensionInput();
		processPensionInput.setAadhaar(1234567890123l);
		processPensionInput.setBankServiceCharge(500);
		processPensionInput.setPensionAmount(22350.0f);
		
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
		processPensionResponse.setResponse(10);
		when(authClient.authorizeTheRequest("token")).thenReturn(false);
		when(pensionDisbursementClient.disbursePension("token", processPensionInput)).thenReturn(processPensionResponse);
//		assertNotNull(processPensionService.pensionProcess("token", processPensionInput));
		assertThrows(TokenExpireException.class, ()->processPensionService.pensionProcess("token", processPensionInput));	
	}
}
