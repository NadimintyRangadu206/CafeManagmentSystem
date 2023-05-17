package com.cafe.managment.main.response;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ResponseObject {

	private Object response;
	private String message;
	private HttpStatus status;
	private List<String> messages;

	public ResponseObject(Exception e) {
		super();
		message = e.getLocalizedMessage();
		status = HttpStatus.INTERNAL_SERVER_ERROR;
	}

	public ResponseObject(Object response) {
		this.response = response;
		status = HttpStatus.OK;
	}

	public ResponseObject(Object response, String message, HttpStatus status) {
		super();
		this.response = response;
		this.message = message;
		this.status = status;
	}

	public ResponseObject(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}

	public ResponseObject(Object response, HttpStatus status, List<String> messages) {
		super();
		this.response = response;
		this.messages = messages;
		this.message = getMessagesString(messages).toString();
		this.status = status;
	}

	public ResponseObject(HttpStatus status, List<String> messages) {
		this.messages = messages;
		this.message = getMessagesString(messages).toString();
		this.status = status;
	}

	public Object getResponse() {
		return response;
	}

	public String getMessage() {
		return message;
	}

	public int getStatus() {
		return this.status.value();
	}

	private StringBuilder getMessagesString(List<String> messages) {
		StringBuilder errorMessage = new StringBuilder("");
		for (String textmessage : messages) {
			if (errorMessage.length() > 0) {
				errorMessage = errorMessage.append(",");
			}
			errorMessage = errorMessage.append(textmessage);
		}
		return errorMessage;
	}

	@Override
	public String toString() {
		return "ResponseObject [response=" + response + ", message=" + message + ", status=" + status + ", messages="
				+ messages + "]";
	}

}
