package com.jlab.education.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlab.education.dao.AccountDao;
import com.jlab.education.dto.MemberDto;

@Service // ������ ������̼ǹ�� -- SERVICE�� �ǹ� : JNB_SERVICE �� ��ü�� ���
public class JlabService {

	@Autowired // DAO�� �������踦 ���� : JNB_DAO �� �����Ŵ
	private AccountDao DAO;

	public void create(MemberDto dto) { // ȸ������
		try {
			DAO.create(dto); // DAO�� �޼ҵ忡 JNB_DTO ��ü�� �Ķ���ͷ� ����
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public String login_check(MemberDto dto) { // �α��� üũ
		String result = null;
		try {
			result = DAO.login_check(dto); // DAO�� �޼ҵ忡 JNB_DTO ��ü�� �Ķ���ͷ� ���� ,
											// ����� String Ÿ������ ���� ����
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	public boolean id_check(String id) { // ���̵� üũ
		boolean flag = true;
		try {
			flag = DAO.id_check(id);
			// DAO�� �޼ҵ忡 JNB_DTO ��ü�� �Ķ���ͷ� ����,
			// ����� boolean Ÿ������ ���Ϲ���
		} catch (Exception e) {
			System.out.println(e);
		}
		return flag;

	}

	public List member_info(String name) { // ���� ����Ʈ
		List list = null;
		try {
			list = DAO.member_info(name); // DAO�� �޼ҵ忡 String Ÿ���� �Ķ���ͷ� ���� , �����
											// List Ÿ������ ���� ����
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public void member_update(Map map) { // ���� ����
		try {
			DAO.member_update(map); // DAO�� �޼ҵ忡 Map Ÿ���� �Ķ���ͷ� ����
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public String get_name(String member_id) { // ���� ���̵� �ش��ϴ� �̸�������
		String name = null;
		try {
			name = DAO.get_name(member_id); // DAO�� �޼ҵ忡 String Ÿ���� �Ķ���ͷ� ���� ,
											// ����� String Ÿ������ ���Ϲ���
		} catch (Exception e) {
			System.out.println(e);
		}
		return name;
	}

	public void delete(String id) { // Ż�� ����
		try {
			DAO.delete(id); // DAO�� �޼ҵ忡 String Ÿ���� �Ķ���ͷ� ����
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
