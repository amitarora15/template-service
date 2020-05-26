package com.amit.service.beans;

public enum ResponseType {
	SUCCESS,
	ERROR,
	WARNING;
	
	public static ResponseType getValue(String name){
		try{
			return ResponseType.valueOf(name);
		} catch(Exception e){
			return ResponseType.ERROR;
		}
	}
}
