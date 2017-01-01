package com.jlab.education.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "article")
@XmlAccessorType(XmlAccessType.FIELD)
public class ArticleDto {

    @XmlElement(name = "num")
	private int num;
    @XmlElement(name = "title")
	private String title;
    @XmlElement(name = "name")
	private String name;
    @XmlElement(name = "content")
	private String content;
    @XmlElement(name = "regdate")
	private String regdate;
    @XmlElement(name = "id")
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
