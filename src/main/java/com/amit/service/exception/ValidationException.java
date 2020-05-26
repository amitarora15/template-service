package com.amit.service.exception;

import com.amit.service.internal.vo.ErrorVo;

public class ValidationException extends BaseException {

	private static final long serialVersionUID = 1L;

	public ValidationException(){
		
	}
	
	public ValidationException(ErrorVo vo) {
		super(vo);
	}

	public ValidationException(ErrorVo vo, Throwable e) {
		super(vo, e);
	}

	public ValidationException(ErrorVo vo, String message) {
		super(vo, message);
	}
	
	public ValidationException(ErrorVo vo, String message, Throwable e) {
		super(vo, message, e);
	}


}
