package com.amit.service.exception;

import com.amit.service.internal.vo.ErrorVo;

public class ServiceException extends BaseException {

	private static final long serialVersionUID = 1L;

	public ServiceException(){
		
	}
	
	public ServiceException(ErrorVo vo) {
		super(vo);
	}

	public ServiceException(ErrorVo vo, Throwable e) {
		super(vo, e);
	}

	public ServiceException(ErrorVo vo, String message) {
		super(vo, message);
	}
	
	public ServiceException(ErrorVo vo, String message, Throwable e) {
		super(vo, message, e);
	}

}
