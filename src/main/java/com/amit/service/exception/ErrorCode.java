package com.amit.service.exception;

import com.amit.service.internal.vo.AbstractErrorCode;

public enum ErrorCode implements AbstractErrorCode {

	INVALID_DATA("invalid.data"),
	;
	
	private String code;
	
	private Integer httpStatus;
	
	private ErrorCode(String code){
		this.code = code;
	}
	
	private ErrorCode(String code, Integer httpStatus){
		this.code = code;
		this.httpStatus = httpStatus;
	}
	
	@Override
	public Integer getHttpCode() {
		return httpStatus;
	}

	@Override
	public String getCode() {
		return code;
	}
	
}
