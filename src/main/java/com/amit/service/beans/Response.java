package com.amit.service.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {"status", "componentMessages", "genericMessages", "entity"})
public class Response<T> {

	
	@JsonProperty(value = "status")
	private ResponseType responseType = ResponseType.SUCCESS;

	@JsonProperty(required = false)
	private Map<String, Map<ResponseType, List<Message>>> componentMessages;
	
	@JsonProperty(required = false)
	private Map<ResponseType, List<Message>> genericMessages;

	private T entity;

	public ResponseType getResponseType() {
		return responseType;
	}

	public void setResponseType(ResponseType status) {
		this.responseType = status;
	}

	public Map<String, Map<ResponseType, List<Message>>> getComponentMessages() {
		if(componentMessages == null)
			componentMessages = new HashMap<>();
		return componentMessages;
	}
	
	public void addWidgetMessage(String widget, ResponseType responseType, Message messsage){
		Map<ResponseType, List<Message>> widgetMessages = getComponentMessages().get(widget);
		if(widgetMessages == null){
			widgetMessages = new HashMap<>();
			getComponentMessages().put(widget, widgetMessages);
		}
		List<Message> responseSpecificMessage = widgetMessages.get(responseType);
		if(responseSpecificMessage == null){
			responseSpecificMessage = new ArrayList<>();
			widgetMessages.put(responseType, responseSpecificMessage);
		}
		responseSpecificMessage.add(messsage);	
	}

	public Map<ResponseType, List<Message>>  getGenericMessage() {
		if(genericMessages == null)
				genericMessages = new HashMap<>();
		return genericMessages;
	}
	
	public void addGenericMessage(ResponseType responseType, Message messsage){
		List<Message> genericMessages = getGenericMessage().get(responseType);
		if(genericMessages == null){
			genericMessages = new ArrayList<>();
			getGenericMessage().put(responseType, genericMessages);
		}
		genericMessages.add(messsage);	
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

}
