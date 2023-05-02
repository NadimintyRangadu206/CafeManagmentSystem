package com.cafe.managment.main.response;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ResponseObject {

	private Object object;
	private HttpStatus status;
	private String message;
	private List<String> messages;

	public ResponseObject(Exception e) {

		message = e.getLocalizedMessage();
		status = HttpStatus.INTERNAL_SERVER_ERROR;
	}

	public ResponseObject(Object response) {

		object = response;
		status = HttpStatus.OK;

	}

	public ResponseObject(Object object, String message, HttpStatus status) {

		this.object = object;
		this.message = message;
		this.status = status;
	}

	public ResponseObject(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}

	public ResponseObject(Object object, List<String> messages, HttpStatus status) {
		this.object = object;
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

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
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
		return "ResponseObject [object=" + object + ", status=" + status + ", message=" + message + ", messages="
				+ messages + "]";
	}

}
