package com.example.javatest.exceptions;

import java.util.List;

/**
 * AppErrorResponse
 */
public class AppErrorResponse {


    private List<Error> errors;

	/**
	 * @return the errors
	 */
	public List<Error> getErrors() {
		return errors;
	}

	/**
	 * @param errors
	 *            the errors to set
	 */
	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	public static class Error {
		private String userMessage;
		private String internalMessage;
		private int code;
		private String more_info;

		/**
		 * @return the userMessage
		 */
		public String getUserMessage() {
			return userMessage;
		}

		/**
		 * @return the internalMessage
		 */
		public String getInternalMessage() {
			return internalMessage;
		}

		/**
		 * @return the code
		 */
		public int getCode() {
			return code;
		}

		/**
		 * @return the more_info
		 */
		public String getMore_info() {
			return more_info;
		}

		/**
		 * @param userMessage
		 *            the userMessage to set
		 */
		public void setUserMessage(String userMessage) {
			this.userMessage = userMessage;
		}

		/**
		 * @param internalMessage
		 *            the internalMessage to set
		 */
		public void setInternalMessage(String internalMessage) {
			this.internalMessage = internalMessage;
		}

		/**
		 * @param code
		 *            the code to set
		 */
		public void setCode(int code) {
			this.code = code;
		}

		/**
		 * @param more_info
		 *            the more_info to set
		 */
		public void setMore_info(String more_info) {
			this.more_info = more_info;
		}

	}
    
}