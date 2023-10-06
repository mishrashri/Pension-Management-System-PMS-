package com.example.PensionDisbursement.model;

import lombok.Data;

@Data
public class ProcessPensionInput {
	private long aadhaar;
	private float pensionAmount;
	private float bankServiceCharge;
	
	
}
