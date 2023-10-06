package com.example.pensionerDetail.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.example.pensionerDetail.exception.AadhaarNotFoundException;
import com.example.pensionerDetail.exception.ErrorResponse;
import com.example.pensionerDetail.exception.TokenExpireException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class MyExceptionAdvisor extends ResponseEntityExceptionHandler
{
	
	@ExceptionHandler(AadhaarNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleExceptionDepartmentNotExist(AadhaarNotFoundException e, HttpServletRequest request)
	{
		ErrorResponse response = new ErrorResponse();
		response.setCode(404);
		response.setMessage(e.getMessage());
		response.setResponseUri(request.getRequestURI());
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TokenExpireException.class)
	public ResponseEntity<ErrorResponse> handleExceptionPatientNotFound(TokenExpireException e, HttpServletRequest request)
	{
		ErrorResponse response = new ErrorResponse();
		response.setCode(404);
		response.setMessage(e.getMessage());
		response.setResponseUri(request.getRequestURI());
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
}
