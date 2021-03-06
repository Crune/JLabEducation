package com.jlab.education.dto;

public class PropDto {

	private String code;
	private String value;

	public PropDto() {
	}
	public PropDto(String code, String value) {
		super();
		this.code = code;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Prop [code=" + code + ", value=" + value + "]";
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
