package com.amit.service.advice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.amit.service.beans.Message;
import com.amit.service.beans.Response;
import com.amit.service.beans.ResponseType;
import com.amit.service.exception.ControllerException;
import com.amit.service.exception.ServiceException;
import com.amit.service.exception.UserIdentityException;
import com.amit.service.exception.ValidationException;
import com.amit.service.internal.vo.ErrorVo;
import com.amit.service.message.MessageService;

@ControllerAdvice(basePackages = { "com.amit.service" })
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@Resource(name = "messageServiceImpl")
	private MessageService messageService;

	private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	private Response<?> getErrorResponse(List<ErrorVo> errors) {
		Response<?> errorResponse = new Response<>();
		errorResponse.setResponseType(ResponseType.ERROR);
		if (errors != null) {
			for (ErrorVo error : errors) {
				Message message = new Message();
				message.setMessage(error.getMessage());
				if (StringUtils.isNotBlank(error.getWidget())) {
					errorResponse.addWidgetMessage(error.getWidget(),
							ResponseType.getValue(error.getResponseType().name()), message);
				} else {
					errorResponse.addGenericMessage(ResponseType.getValue(error.getResponseType().name()), message);
				}
			}
		} else {
			Message message = new Message("Internal Server Error in processing your request.");
			errorResponse.addGenericMessage(ResponseType.ERROR, message);
		}
		return errorResponse;
	}

	private HttpStatus getHttpStatus(List<ErrorVo> errors) {
		HttpStatus httpStatus = null;
		Set<Integer> statuses = new HashSet<>();
		if (errors != null) {
			for (ErrorVo error : errors) {
				if (error.getHttpCode() != null)
					statuses.add(error.getHttpCode());
			}
			if (statuses.size() == 1) {
				Integer httpCode = null;
				try {
					httpCode = statuses.toArray(new Integer[statuses.size()])[0];
					httpStatus = HttpStatus.valueOf(httpCode);
				} catch (Exception e) {
					LOG.error("Error while parsing http Code to HTTP Status code - " + httpCode, e);
				}
			}
		}
		return httpStatus;
	}

	@ExceptionHandler(Throwable.class)
	@ResponseBody
	public ResponseEntity<Object> handleException(HttpServletRequest req, Throwable ex) {
		Response<?> errorResponse = getErrorResponse(null);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ValidationException.class)
	@ResponseBody
	public ResponseEntity<Object> handleValidationException(HttpServletRequest req, ValidationException ex) {
		Response<?> errorResponse = getErrorResponse(ex.getErrors());
		HttpStatus httpStatus = getHttpStatus(ex.getErrors());
		if (httpStatus == null) {
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(errorResponse, httpStatus);
	}

	@ExceptionHandler(ControllerException.class)
	@ResponseBody
	public ResponseEntity<Object> handleControllerException(HttpServletRequest req, ControllerException ex) {
		Response<?> errorResponse = getErrorResponse(ex.getErrors());
		HttpStatus httpStatus = getHttpStatus(ex.getErrors());
		if (httpStatus == null) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(errorResponse, httpStatus);

	}

	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public ResponseEntity<Object> handleServiceException(HttpServletRequest req, ServiceException ex) {
		Response<?> errorResponse = getErrorResponse(ex.getErrors());
		HttpStatus httpStatus = getHttpStatus(ex.getErrors());
		if (httpStatus == null) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(errorResponse, httpStatus);

	}

	@ExceptionHandler(UserIdentityException.class)
	@ResponseBody
	public ResponseEntity<Object> handleUserIdentityException(HttpServletRequest req, UserIdentityException ex) {
		Response<?> errorResponse = getErrorResponse(ex.getErrors());
		HttpStatus httpStatus = getHttpStatus(ex.getErrors());
		if (httpStatus == null) {
			httpStatus = HttpStatus.FORBIDDEN;
		}
		return new ResponseEntity<>(errorResponse, httpStatus);
	}

	/**
	 * Customize the response for MethodArgumentNotValidException.
	 */
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		LOG.error("Validation Error of Request Object : " + ex.getMessage());
		BindingResult result = ex.getBindingResult();
		List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
		List<ErrorVo> errors = new ArrayList<>();
		fieldErrors.stream().forEach(a -> errors
				.add(new ErrorVo(a.getField(), messageService.getMessage(a.getDefaultMessage(), null, null, null))));
		Response<?> errorResponse = getErrorResponse(errors);
		return handleExceptionInternal(ex, errorResponse, headers, status, request);
	}

}
