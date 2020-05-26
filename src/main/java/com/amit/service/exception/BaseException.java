package com.amit.service.exception;

import java.util.ArrayList;
import java.util.List;

import com.amit.service.internal.vo.ErrorVo;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	protected List<ErrorVo> errors;
	
	public BaseException(){
		
	}

	public BaseException(ErrorVo vo) {
		addError(vo);
	}

	public BaseException(ErrorVo vo, Throwable e) {
		super(e);
		addError(vo);
	}

	public BaseException(ErrorVo vo, String message) {
		super(message);
		addError(vo);
	}
	
	public BaseException( ErrorVo vo, String message, Throwable e) {
		super(message, e);
		addError(vo);
	}

	public List<ErrorVo> getErrors() {
		if (errors == null) {
			errors = new ArrayList<>();
		}
		return errors;
	}

	public void addError(ErrorVo vo) {
		if (errors == null) {
			errors = new ArrayList<>();
		}
		errors.add(vo);
	}
	
	@Override
	public String toString(){
		return this.errors.toString();
	}
	
}
