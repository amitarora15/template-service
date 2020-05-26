package com.amit.service.internal.vo;

import java.io.Serializable;

public class ErrorVo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String widget;
	
	private String message;
	
	private Integer httpCode;
	
	private ResponseTypeVo responseType = ResponseTypeVo.ERROR;
	
	public ErrorVo(String message){
		this.message = message;
	}
	
	public ErrorVo(String message, Integer httpCode){
		this(message);
		this.httpCode = httpCode;
	}
	
	public ErrorVo(String widget, String message){
		this(message);
		this.widget = widget;
	}
	
	public ErrorVo(String widget, String message, Integer httpCode){
		this(widget, message);
		this.httpCode = httpCode;
	}

	public String getWidget() {
		return widget;
	}

	public void setWidget(String widget) {
		this.widget = widget;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(Integer httpCode) {
		this.httpCode = httpCode;
	}

	public ResponseTypeVo getResponseType() {
		return responseType;
	}

	public void setResponseType(ResponseTypeVo responseType) {
		this.responseType = responseType;
	}

	@Override
	public String toString() {
		return message + ": (Optional http code - " + httpCode + ")";
	}

}
