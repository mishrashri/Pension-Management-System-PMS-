package com.example.PensionDisbursement.model;

import lombok.Data;

@Data
public class PensionerDetail {
	
	private long aadhaar;
	private String name;
	private java.sql.Date dob;
	private String pan;
	private float lastSalary;
	private float allowance;
	private String pensionType;
	private String bankName;
	private long acccountNo;
	private String bankType;
}
