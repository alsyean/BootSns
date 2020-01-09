package com.sns.pjt.Controller.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ResultDto {

	private int code;
	private String message;
	private Object data;
	
	public ResultDto(String message) {
		this.message = message;
	}
	
	public ResultDto(int code , String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

}
