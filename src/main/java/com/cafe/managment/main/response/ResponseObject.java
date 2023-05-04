package com.cafe.managment.main.response;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ResponseObject {

	private Object response;
	private HttpStatus status;
	private String message;
	private List<String> messages;

	public ResponseObject(Exception e) {

		message = e.getLocalizedMessage();
		status = HttpStatus.INTERNAL_SERVER_ERROR;
	}

	public ResponseObject(Object response) {

		
		this.response = response;
		status = HttpStatus.OK;

	}

	public ResponseObject(Object response, String message, HttpStatus status) {

		this.response = response;
		this.message = message;
		this.status = status;
	}

	public ResponseObject(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}

	public ResponseObject(Object response, List<String> messages, HttpStatus status) {
		this.response = response;
		this.messages = messages;
		this.message = getMessagesString(messages).toString();
		this.status = status;
	}

	private Object getMessagesString(List<String> messages2) {
		StringBuilder errorMessage = new StringBuilder("");
		for (String textmessage : messages) {
			if (errorMessage.length() > 0) {
				errorMessage = errorMessage.append(",");
			}
			errorMessage = errorMessage.append(textmessage);
		}
		return errorMessage;
	}

	public Object getResponse() {
		return response;
	}

	public void setObject(Object response) {
		this.response = response;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "ResponseObject [response=" + response + ", status=" + status + ", message=" + message + ", messages="
				+ messages + "]";
	}

}
