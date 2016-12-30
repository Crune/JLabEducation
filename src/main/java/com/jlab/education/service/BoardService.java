package com.jlab.education.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlab.education.dao.BoardDAO;
import com.jlab.education.dto.ArticleDto;
import com.jlab.education.dto.ReplyDto;

@Service //������ ������̼ǹ�� -- Service �� �ǹ� : gesipan_SERVICE �� ��ü�� ���
public class BoardService {
	@Autowired //������ ������̼��� DI��� -- ���踦 �ξ��־� DAO �� ��ü�� ��� : gesipan_DAO �� �����Ų��
	private BoardDAO DAO;

	// �Խ��� �۾���
	public void gesipan_insert(ArticleDto dto) {
		try {
			DAO.g_insert(dto);// �Ѿ�� DTO������ �̿��Ͽ� DB�� ���� insert
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// �Խ��� List
	public List gesi_list() {
		List list = null;// List�� �ʱ�ȭ.
		try {
			list = DAO.gesipan_list();// DB�� �����Ͽ� ��ȸ�� �Խù��� List�� ���
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	// �Խù� �󼼺���
	public ArticleDto gesi_rview(int num) {
		ArticleDto data = new ArticleDto();// DTO�� ����ϱ� ���� ���
		try {
			data = DAO.g_review(num);// num�̶�� ������ȣ�� ���Ͽ� �Խù��� ������ 
		} catch (Exception e) {// ��ȸ�ϰ� �̸� DTO�� ����.
			System.out.println(e);
		}
		return data;
	}

	// �Խù� ������Ʈ
	public void gesi_update(ArticleDto dto) {
		try {
			DAO.g_update(dto);// DTO�� ������ �̿��Ͽ� ���Ӱ� DB���� Update
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// �Խù� ����
	public void gesi_delete(int num) {
		try {
			DAO.g_delete(num);// num�̶�� ������ȣ�� �̿��Ͽ� �Խù��� ����.
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// �����ȸ
	public List gesi_rview_board(int num) {
		List list = null;// List�� �ʱ�ȭ
		try {
			list = DAO.gesi_rview_board(num);// �ش� ������ȣ�Խù��� ��۵��� ��ȸ.
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	

	// ��۾���
	public void gesi_rview_insert(ReplyDto dto) {
		try {
			DAO.gesi_rview_insert(dto);// �Ѿ�� DTO������ �̿��Ͽ� DB�� ���� insert
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
