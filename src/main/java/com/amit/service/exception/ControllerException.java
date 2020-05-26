package com.amit.service.exception;

import com.amit.service.internal.vo.ErrorVo;

public class ControllerException extends BaseException {

	private static final long serialVersionUID = 1L;

	public ControllerException(){
		
	}
	
	public ControllerException(ErrorVo vo) {
		super(vo);
	}

	public ControllerException(ErrorVo vo, Throwable e) {
		super(vo, e);
	}

	public ControllerException(ErrorVo vo, String message) {
		super(vo, message);
	}
	
	public ControllerException(ErrorVo vo, String message, Throwable e) {
		super(vo, message, e);
	}
}
