package com.example.processPension.model;

import lombok.Data;

@Data
public class PensionerInput {
	private String name;
	private java.sql.Date dob;
	private String pan;
	private Long aadhaar;
	private String pensionType;
}
