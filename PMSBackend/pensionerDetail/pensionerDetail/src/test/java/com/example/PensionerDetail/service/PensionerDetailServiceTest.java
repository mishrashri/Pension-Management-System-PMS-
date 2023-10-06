package com.example.PensionerDetail.service;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.pensionerDetail.client.AuthClient;
import com.example.pensionerDetail.entity.PensionerDetail;
import com.example.pensionerDetail.exception.AadhaarNotFoundException;
import com.example.pensionerDetail.exception.TokenExpireException;
import com.example.pensionerDetail.repository.PensionerDetailRepository;
import com.example.pensionerDetail.service.PensionerDetailService;

@AutoConfigureMockMvc
@SpringBootTest
public class PensionerDetailServiceTest {
	
	@InjectMocks
	PensionerDetailService pensionerDetailService;
	
	@Mock
	AuthClient authClient;
	
	@Mock
	PensionerDetailRepository pensionerDetailRepository;
	

	@Test
	public void testPensionerDetailByAadhaarSuccess() throws AadhaarNotFoundException, TokenExpireException
	{
		PensionerDetail pensionerDetail = new PensionerDetail();
		pensionerDetail.setAadhaar(1234567890123l);
		pensionerDetail.setAcccountNo(987654321);
		pensionerDetail.setBankType("public");
		
		when(authClient.authorizeTheRequest("token")).thenReturn(true);
		when(pensionerDetailRepository.getPensionerDetail(1234567890123l)).thenReturn(pensionerDetail);
		
		assertNotNull(pensionerDetailService.pensionerDetailByAadhaar("token", 1234567890123l));
		assertEquals("public", pensionerDetail.getBankType());	 		 
	}
	
	@Test
	public void testPensionerDetailByAadhaarFail() throws AadhaarNotFoundException, TokenExpireException
	{
		PensionerDetail pensionerDetail = new PensionerDetail();
		pensionerDetail.setAadhaar(1234567890123l);
		pensionerDetail.setAcccountNo(987654321);
		
		when(authClient.authorizeTheRequest("token")).thenReturn(true);
		when(pensionerDetailRepository.getPensionerDetail(1234567890123l)).thenReturn(null);
		
		assertThrows(AadhaarNotFoundException.class, ()->pensionerDetailService.pensionerDetailByAadhaar("token", 1234567890123l));	 		 
	}
	
	@Test
	public void testPensionerDetailByAadhaarTokenFail() throws AadhaarNotFoundException, TokenExpireException
	{
		PensionerDetail pensionerDetail = new PensionerDetail();
		pensionerDetail.setAadhaar(1234567890123l);
		pensionerDetail.setAcccountNo(987654321);
		
		when(authClient.authorizeTheRequest("token")).thenReturn(false);
		when(pensionerDetailRepository.getPensionerDetail(1234567890123l)).thenReturn(null);
		
		assertThrows(TokenExpireException.class, ()->pensionerDetailService.pensionerDetailByAadhaar("token", 1234567890123l));	 		 
	}
}
//1234567890123,987654321,1234,"PNB","public",'1999-08-05',39999,"Harsh","AUIPH1121D","family