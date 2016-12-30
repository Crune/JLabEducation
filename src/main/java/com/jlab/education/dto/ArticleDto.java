package com.jlab.education.dto;

public class ArticleDto {

	private int num;
	private String title;
	private String name;
	private String content;
	private String regdate;
	private String id;
	
	public ArticleDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArticleDto(int num, String title, String name, String content,
			String regdate) {
		super();
		this.num = num;
		this.title = title;
		this.name = name;
		this.content = content;
		this.regdate = regdate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
