package com.example.PensionDisbursement.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.PensionDisbursement.model.PensionerDetail;

@FeignClient(name="pensionerDetail", url = "http://localhost:8080")
public interface PensionDetailClient {
	
	@GetMapping("/pensionerdetailbyaadhaar")
	public PensionerDetail pensionerDetailByAadhaar(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader, @RequestParam("aadhaar") long aadhaar);
}
