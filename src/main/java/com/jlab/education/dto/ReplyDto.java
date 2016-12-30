package com.jlab.education.dto;

public class ReplyDto {
	private int gasinumber;
	private int num;
	private String name;
	private String content;
	private String id;
	private String regdate;

	public ReplyDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReplyDto(int gasinumber, int num, String name, String content, String regdate) {
		super();
		this.num = num;
		this.gasinumber = gasinumber;
		this.name = name;
		this.content = content;
		this.regdate = regdate;
	}
	
	public int getGasinumber() {
		return gasinumber;
	}
	public void setGasinumber(int gasinumber) {
		this.gasinumber = gasinumber;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
}
