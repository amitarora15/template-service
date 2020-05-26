package com.amit.service.message.impl;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.amit.service.internal.vo.AbstractErrorCode;
import com.amit.service.internal.vo.ErrorVo;
import com.amit.service.message.MessageService;

@Component
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageSource source;
	
	@Override
	public String getMessage(String code, Object[] parameters, String language, String country) {
		Locale locale = LocaleContextHolder.getLocale();
		if(StringUtils.isNotBlank(language) && StringUtils.isNotBlank(country)){
			locale = new Locale(language, country);
		}
		if(parameters != null && parameters.length > 0){
			return source.getMessage(code, parameters, locale);	
		} else {
			return source.getMessage(code, null, locale);
		}
		
	}
	
	@Override
	public ErrorVo getErrorVoForWidget(String widget, AbstractErrorCode errorCode, Object[] parameters,
			String language, String country) {
		String errorMessage = this.getMessage(errorCode.getCode(), parameters, language, country);
		return new ErrorVo(widget, errorMessage, errorCode.getHttpCode());
	}

	@Override
	public ErrorVo getErrorVo(AbstractErrorCode errorCode, Object[] parameters, String language, String country) {
		String errorMessage = this.getMessage(errorCode.getCode(), parameters, language, country);
		return new ErrorVo(errorMessage, errorCode.getHttpCode());
	}

}
