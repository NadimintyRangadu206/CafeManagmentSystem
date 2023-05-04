package com.cafe.managment.main.exception;

public class CafeException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	private final int errorCode;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public CafeException(int errorCode, String message) {

		super(message);
		this.errorCode = errorCode;

	}

	@Override
	public String toString() {
		return "CafeException [errorCode=" + errorCode + "]";
	}


}
