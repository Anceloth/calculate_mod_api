package com.inclusioncloud.rest.core.entities;

public class ResponseDTO {
	
	private Double response;
	
	public ResponseDTO() {
	}

	public ResponseDTO(Double response) {
		super();
		this.response = response;
	}

	@Override
	public String toString() {
		return "ResponseDTO [response=" + response + "]";
	}

	public Double getResponse() {
		return response;
	}

	public void setResponse(Double response) {
		this.response = response;
	}
	
}
