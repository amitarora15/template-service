package com.amit.service.message;

import com.amit.service.internal.vo.AbstractErrorCode;
import com.amit.service.internal.vo.ErrorVo;

public interface MessageService {
	
	String getMessage(String code, Object[] parameters, String language, String country);
	
	ErrorVo getErrorVoForWidget(String widget, AbstractErrorCode errorCode, Object[] parameters,
			String language, String country);
	
	ErrorVo getErrorVo(AbstractErrorCode errorCode, Object[] parameters, String language, String country);
}
