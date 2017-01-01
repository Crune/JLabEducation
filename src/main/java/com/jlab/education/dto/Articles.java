package com.jlab.education.dto;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "articles")
@XmlAccessorType (XmlAccessType.FIELD)
public class Articles {

    @XmlElement(name = "article")
    private List<ArticleDto> articleDtoList = null;

    public List<ArticleDto> getArticles() {
        return articleDtoList;
    }

    public void setArticles(List<ArticleDto> articleDtoList) {
        this.articleDtoList = articleDtoList;
    }
}