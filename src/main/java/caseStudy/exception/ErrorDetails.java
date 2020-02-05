package caseStudy.exception;

import java.io.Serializable;

public class ErrorDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String code;
	String description;
	String request;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

}
