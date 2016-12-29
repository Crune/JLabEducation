package com.jlab.education.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlab.education.dao.AccountDao;
import com.jlab.education.dto.MemberDto;

@Service // 스프링 어노테이션방식 -- SERVICE를 의미 : JNB_SERVICE 를 빈객체로 등록
public class JlabService {

	@Autowired // DAO와 의존관계를 맺음 : JNB_DAO 를 연결시킴
	private AccountDao DAO;

	public void create(MemberDto dto) { // 회원가입
		try {
			DAO.create(dto); // DAO의 메소드에 JNB_DTO 객체를 파라메터로 전달
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public String login_check(MemberDto dto) { // 로그인 체크
		String result = null;
		try {
			result = DAO.login_check(dto); // DAO의 메소드에 JNB_DTO 객체를 파라메터로 전달 ,
											// 결과를 String 타입으로 리턴 받음
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	public boolean id_check(String id) { // 아이디 체크
		boolean flag = true;
		try {
			flag = DAO.id_check(id);
			// DAO의 메소드에 JNB_DTO 객체를 파라메터로 전달,
			// 결과를 boolean 타입으로 리턴받음
		} catch (Exception e) {
			System.out.println(e);
		}
		return flag;

	}

	public List member_info(String name) { // 정보 리스트
		List list = null;
		try {
			list = DAO.member_info(name); // DAO의 메소드에 String 타입을 파라메터로 전달 , 결과를
											// List 타입으로 리턴 받음
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public void member_update(Map map) { // 정보 수정
		try {
			DAO.member_update(map); // DAO의 메소드에 Map 타입을 파라메터로 전달
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public String get_name(String member_id) { // 세션 아이디에 해당하는 이름값추출
		String name = null;
		try {
			name = DAO.get_name(member_id); // DAO의 메소드에 String 타입을 파라메터로 전달 ,
											// 결과를 String 타입으로 리턴받음
		} catch (Exception e) {
			System.out.println(e);
		}
		return name;
	}

	public void delete(String id) { // 탈퇴 진행
		try {
			DAO.delete(id); // DAO의 메소드에 String 타입을 파라메터로 전달
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
