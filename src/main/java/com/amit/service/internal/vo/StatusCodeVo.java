package com.amit.service.internal.vo;

import java.io.Serializable;

public class StatusCodeVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public StatusCodeVo(String message) {
		this.message = message;
	}

	public StatusCodeVo(String message, Integer httpCode) {
		this(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
