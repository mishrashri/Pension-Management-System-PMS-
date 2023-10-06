package com.example.PensionerDetail.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.example.pensionerDetail.client.AuthClient;
import com.example.pensionerDetail.controller.PensionerDetailController;
import com.example.pensionerDetail.entity.PensionerDetail;
import com.example.pensionerDetail.service.PensionerDetailService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
@AutoConfigureMockMvc
@SpringBootTest
public class PensionerDetailControllerTest {
	
	@InjectMocks
	PensionerDetailController pensionerDetailController;
	
	@Autowired
	MockMvc mockMvc;
	
	@Mock
	PensionerDetailService pensionerDetailService;
	
	
	PensionerDetail pensionerDetail;
	
	@Test
	public void contextLoad()
	{
		assertNotNull(pensionerDetailService);
	}
	
	@Test
	public void testPensionerDetailByAadhaar() throws Exception
	{
		PensionerDetail pensionerDetail = new PensionerDetail();
		pensionerDetail.setAadhaar(1234567890123l);
		pensionerDetail.setAcccountNo(987654321);
		
		when(pensionerDetailService.pensionerDetailByAadhaar("token", 1234567890123l)).thenReturn(pensionerDetail);
		
		assertNotNull(pensionerDetailController.pensionerDetailByAadhaar("token", 1234567890123l));
		
//		ResultActions action = mockMvc.perform(get("/pensionerdetailbyaadhaar?aadhaar=1234567890123"));
//		action.andExpect(status().isOk());
//		action.andExpect(jsonPath("$.aadhaar").exists());
//		action.andExpect(jsonPath("$.aadhaar").value(1234567890123l));
//		action.andExpect(jsonPath("$.name").exists());
//		action.andExpect(jsonPath("$.name").value("Harsh"));
//		action.andExpect(jsonPath("$.dob").exists());
//		action.andExpect(jsonPath("$.dob").value("1999-08-05"));
//		action.andExpect(jsonPath("$.lastSalary").exists());
//		action.andExpect(jsonPath("$.lastSalary").value(39999));
//		action.andExpect(jsonPath("$.allowance").exists());
//		action.andExpect(jsonPath("$.allowance").value(1234));
//		action.andExpect(jsonPath("$.pensionType").exists());
//		action.andExpect(jsonPath("$.pensionType").value("family"));
//		action.andExpect(jsonPath("$.bankName").exists());
//		action.andExpect(jsonPath("$.bankName").value("PNB"));
//		action.andExpect(jsonPath("$.accountNo").exists());
//		action.andExpect(jsonPath("$.accountNo").value(987654321));
//		action.andExpect(jsonPath("$.bankType").exists());
//		action.andExpect(jsonPath("$.bankType").value("public"));
//		1234567890123,987654321,1234,"PNB","public",'1999-08-05',39999,"Harsh","AUIPH1121D","family
		
	}

}
//private long aadhaar;
//private String name;
//private Date dob;
//private String pan;
//private float lastSalary;
//private float allowance;
//private String pensionType;
//private String bankName;
//private long acccountNo;
//private String bankType;
