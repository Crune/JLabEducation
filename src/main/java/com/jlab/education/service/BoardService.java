package com.jlab.education.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlab.education.dao.BoardDAO;
import com.jlab.education.dto.ArticleDto;
import com.jlab.education.dto.ReplyDto;

@Service //스프링 어노테이션방식 -- Service 를 의미 : gesipan_SERVICE 를 빈객체로 등록
public class BoardService {
	@Autowired //스프링 어노테이션의 DI방식 -- 관계를 맺어주어 DAO 를 빈객체로 등록 : gesipan_DAO 를 연결시킨다
	private BoardDAO DAO;

	// 게시판 글쓰기
	public void gesipan_insert(ArticleDto dto) {
		try {
			DAO.g_insert(dto);// 넘어온 DTO정보를 이용하여 DB에 값을 insert
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// 게시판 List
	public List gesi_list() {
		List list = null;// List를 초기화.
		try {
			list = DAO.gesipan_list();// DB에 접근하여 조회한 게시물을 List에 담기
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	// 게시물 상세보기
	public ArticleDto gesi_rview(int num) {
		ArticleDto data = new ArticleDto();// DTO를 사용하기 위해 등록
		try {
			data = DAO.g_review(num);// num이라는 고유번호를 통하여 게시물을 정보를 
		} catch (Exception e) {// 조회하고 이를 DTO에 담음.
			System.out.println(e);
		}
		return data;
	}

	// 게시물 업데이트
	public void gesi_update(ArticleDto dto) {
		try {
			DAO.g_update(dto);// DTO의 정보를 이용하여 새롭게 DB내용 Update
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// 게시물 삭제
	public void gesi_delete(int num) {
		try {
			DAO.g_delete(num);// num이라는 고유번호를 이용하여 게시물을 삭제.
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// 댓글조회
	public List gesi_rview_board(int num) {
		List list = null;// List를 초기화
		try {
			list = DAO.gesi_rview_board(num);// 해당 고유번호게시물의 댓글들을 조회.
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	

	// 댓글쓰기
	public void gesi_rview_insert(ReplyDto dto) {
		try {
			DAO.gesi_rview_insert(dto);// 넘어온 DTO정보를 이용하여 DB에 값을 insert
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
