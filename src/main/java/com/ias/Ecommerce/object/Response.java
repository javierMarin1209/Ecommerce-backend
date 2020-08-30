package com.ias.Ecommerce.object;

public class Response {
	
	private boolean success;
	private String Error;
	private Object response;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getError() {
		return Error;
	}
	public void setError(String error) {
		Error = error;
	}
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
	@Override
	public String toString() {
		return "Response [success=" + success + ", Error=" + Error + ", response=" + response + ", isSuccess()="
				+ isSuccess() + ", getError()=" + getError() + ", getResponse()=" + getResponse() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}

