package com.javero.redispring.controller;

public class Response {
	
	public static final int NO_ERROR = 0;
	public static final int NOT_FOUND = 404;
	
	public static final String NO_MESSAGE = "Not Found";
	
	private Error error;
	
	

	public Response(Error error) {
		this.error = error;
	}



	public Error getError() {
		return error;
	}



	public void setError(Error error) {
		this.error = error;
	}



	public static int getNoError() {
		return NO_ERROR;
	}



	public static int getNotFound() {
		return NOT_FOUND;
	}



	public static String getNoMessage() {
		return NO_MESSAGE;
	}



	static class Error {
		private long errorCode;
		private String message;
	
		
		public Error(long errorCode, String message) {
			this.errorCode = errorCode;
			this.message = message;
		}

		public long getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(long errorCode) {
			this.errorCode = errorCode;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
		
		//Metodos clase interna
		public static Response noErrorResponse() {
			return new Response(new Error(NO_ERROR, NO_MESSAGE));
		}
		
		public static Response errorResponse(int errorCode, String errorMessage) {
			return new Response(new Error(errorCode, errorMessage));
		}

}
