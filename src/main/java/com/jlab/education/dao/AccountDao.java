package com.jlab.education.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.jlab.education.dto.MemberDto;

@Repository		//스프링 어노테이션방식 -- DAO 를 의미	 : JNB_DAO 를 빈객체로 등록
public class AccountDao extends SqlMapClientDaoSupport{
	
	@Autowired		//IBATIS를 로딩함
	public AccountDao(SqlMapClient sqlMapClient){		//IBATIS를 로딩함
		setSqlMapClient(sqlMapClient);
	}
	
	public void create(MemberDto dto) {
		getSqlMapClientTemplate().update("member.create",dto);		
	}

	public String login_check(MemberDto dto) {
		return (String) getSqlMapClientTemplate().queryForObject("member.login_check",dto);
	}

	public boolean id_check(String id) {
		boolean flag = false;
		String temp = null;
		System.out.println("a 값 : "+temp);
		temp = (String)getSqlMapClientTemplate().queryForObject("member.id_check",id);
		
		System.out.println("a 값 : "+temp);
		
		if (temp == null) flag = true;
		return flag;
	}

	public List member_info(String name) {
		return getSqlMapClientTemplate().queryForList("member.member_info",name);	
	}

	public void member_update(Map map) {
		getSqlMapClientTemplate().update("member.member_update",map);	
	}

	public String get_name(String member_id) {
		return (String)getSqlMapClientTemplate().queryForObject("member.get_name",member_id);	
	}

	public void delete(String id) {
		getSqlMapClientTemplate().delete("member.delete",id);
	}
	
}
