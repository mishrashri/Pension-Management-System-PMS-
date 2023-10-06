package com.example.pensionerDetail.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PensionerDetail {
	@Id
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
