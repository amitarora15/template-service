package com.amit.service.exception;

import com.amit.service.internal.vo.ErrorVo;

public class UserIdentityException extends BaseException {

	private static final long serialVersionUID = 1L;

	public UserIdentityException(){
		
	}
	
	public UserIdentityException(ErrorVo vo) {
		super(vo);
	}

	public UserIdentityException(ErrorVo vo, Throwable e) {
		super(vo, e);
	}

	public UserIdentityException(ErrorVo vo, String message) {
		super(vo, message);
	}
	
	public UserIdentityException(ErrorVo vo, String message, Throwable e) {
		super(vo, message, e);
	}

}
