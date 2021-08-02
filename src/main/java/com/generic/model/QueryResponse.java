package com.generic.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class QueryResponse {

	private Date timestamp;
	private String message;
	private int statusCode;
	private boolean success;
	
    @JsonInclude(Include.NON_NULL)
	private List<?> results;
    
    @JsonInclude(Include.NON_NULL)
	private Object object;
	
	public QueryResponse() {
		super();
	}

	public QueryResponse(Date timestamp, String message, int statusCode, boolean success, List<?> results) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.statusCode = statusCode;
		this.success = success;
		this.results = results;
	}

	public QueryResponse(Date timestamp, String message, int statusCode, boolean success, Object object) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.statusCode = statusCode;
		this.success = success;
		this.object = object;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<?> getResults() {
		return results;
	}

	public void setResults(List<?> results) {
		this.results = results;
	}

	@JsonGetter("user")
	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	
}
