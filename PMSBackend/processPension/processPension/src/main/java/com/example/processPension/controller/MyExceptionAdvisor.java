package com.example.processPension.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.example.processPension.exception.ErrorResponse;
import com.example.processPension.exception.PensionerNotFoundException;
import com.example.processPension.exception.TokenExpireException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class MyExceptionAdvisor extends ResponseEntityExceptionHandler
{
	
	@ExceptionHandler(PensionerNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleExceptionDepartmentNotExist(PensionerNotFoundException e, HttpServletRequest request)
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
