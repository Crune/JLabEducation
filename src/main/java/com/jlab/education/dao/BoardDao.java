package com.jlab.education.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.jlab.education.dto.ArticleDto;
import com.jlab.education.dto.ReplyDto;

@Repository //스프링 어노테이션방식 -- DAO 를 의미 : gasipan_DAO 를 빈객체로 등록
public class BoardDao extends SqlMapClientDaoSupport {
	@Autowired //IBATIS를 로딩함
	public BoardDao(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	// 게시판
	public List<?> gesipan_list() {
		return getSqlMapClientTemplate().queryForList("board.list");
	}

	// 글쓰기
	public void g_insert(ArticleDto dto) {
		getSqlMapClientTemplate().insert("board.write", dto);
	}

	// 상세보기
	public ArticleDto g_review(int num) {
		return (ArticleDto) getSqlMapClientTemplate().queryForObject("board.rview", num);
	}

	// 업데이트
	public void g_update(ArticleDto dto) {
		getSqlMapClientTemplate().update("board.update", dto);
	}

	// 삭제
	public void g_delete(int num) {
		getSqlMapClientTemplate().delete("board.delete", num);
	}

	//댓글조회
	public List<?> gesi_rview_board(int num) {
		return getSqlMapClientTemplate().queryForList("board.review_board", num);
	}

	// 댓글상세보기
	public ReplyDto pick_review(int num) {
		return (ReplyDto) getSqlMapClientTemplate().queryForObject("board.pick_review", num);
	}

	// 댓글쓰기
	public void gesi_rview_insert(ReplyDto dto) {
		getSqlMapClientTemplate().insert("board.review_write", dto);
	}
	
	// 댓글삭제
	public void gesi_rview_delete(int num) {
		getSqlMapClientTemplate().delete("board.review_delete", num);
	}

}
