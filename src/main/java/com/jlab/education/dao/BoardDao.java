package com.jlab.education.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.jlab.education.dto.ArticleDto;
import com.jlab.education.dto.ReplyDto;

@Repository //������ ������̼ǹ�� -- DAO �� �ǹ� : gasipan_DAO �� ��ü�� ���
public class BoardDao extends SqlMapClientDaoSupport {
	@Autowired //IBATIS�� �ε���
	public BoardDao(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	// �Խ���
	public List<?> gesipan_list() {
		return getSqlMapClientTemplate().queryForList("board.list");
	}

	// �۾���
	public void g_insert(ArticleDto dto) {
		getSqlMapClientTemplate().insert("board.write", dto);
	}

	// �󼼺���
	public ArticleDto g_review(int num) {
		return (ArticleDto) getSqlMapClientTemplate().queryForObject("board.rview", num);
	}

	// ������Ʈ
	public void g_update(ArticleDto dto) {
		getSqlMapClientTemplate().update("board.update", dto);
	}

	// ����
	public void g_delete(int num) {
		getSqlMapClientTemplate().delete("board.delete", num);
	}

	//�����ȸ
	public List<?> gesi_rview_board(int num) {
		return getSqlMapClientTemplate().queryForList("board.review_board", num);
	}

	// ��ۻ󼼺���
	public ReplyDto pick_review(int num) {
		return (ReplyDto) getSqlMapClientTemplate().queryForObject("board.pick_review", num);
	}

	// ��۾���
	public void gesi_rview_insert(ReplyDto dto) {
		getSqlMapClientTemplate().insert("board.review_write", dto);
	}
	
	// ��ۻ���
	public void gesi_rview_delete(int num) {
		getSqlMapClientTemplate().delete("board.review_delete", num);
	}

}
