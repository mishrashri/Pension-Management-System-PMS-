package com.example.processPension.model;

import lombok.Data;

@Data
public class ProcessPensionInput {
	
	private Long aadhaar;
	private float pensionAmount;
	private float bankServiceCharge;
}
