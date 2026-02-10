package com.jsp.ems.util;

public class ResponseStructure<T> {
	private int statusCode;
	private String message;
	private T t;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	public ResponseStructure(int statusCode, String message, T t) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.t = t;
	}
}
	
	
