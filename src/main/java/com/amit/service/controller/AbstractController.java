package com.amit.service.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amit.service.beans.Response;
import com.amit.service.beans.ResponseType;
import com.amit.service.internal.vo.AbstractErrorCode;
import com.amit.service.internal.vo.ErrorVo;
import com.amit.service.message.MessageService;

public abstract class AbstractController {
	
	@Resource(name = "messageServiceImpl")
	private MessageService messageService;
	
	private static final Logger LOG = LoggerFactory.getLogger(AbstractController.class);
	
	protected <T> Response<T> prepareSuccessResponse(T bean) {
		Response<T> response = new Response<>();
		response.setResponseType(ResponseType.SUCCESS);
		response.setEntity(bean);
		return response;
	}

	protected void handleException(Throwable e) throws Throwable {
		LOG.error(e.getMessage(), e);
		throw e;	
	}
	
	protected ErrorVo getErrorVoForWidget(String widget, AbstractErrorCode errorCode, Object[] parameters, String language, String country){
		return messageService.getErrorVoForWidget(widget, errorCode, parameters, language, country);
	}
	
	protected ErrorVo getErrorVo(AbstractErrorCode errorCode, Object[] parameters, String language, String country){
		return messageService.getErrorVo(errorCode, parameters, language, country);
	}
	
}
