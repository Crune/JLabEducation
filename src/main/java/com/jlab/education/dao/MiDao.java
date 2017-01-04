package com.jlab.education.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.jlab.education.dto.PropDto;
import com.jlab.education.dto.SawonDto;

@Repository
@SuppressWarnings("unchecked")
public class MiDao extends SqlMapClientDaoSupport {
	
	@Autowired
	public MiDao(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	public List<SawonDto> findSawon(String name) {
		return getSqlMapClientTemplate().queryForList("mi.sawonFindList", name);
	}
	
	public List<PropDto> readAllDepts() {
		return getSqlMapClientTemplate().queryForList("mi.deptList");
	}

	public List<PropDto> readAllJikgups() {
		return getSqlMapClientTemplate().queryForList("mi.jikgupList");
	}

	public void insert(SawonDto dto) {
		getSqlMapClientTemplate().insert("mi.insert", dto);
	}

	public void update(SawonDto dto) {
		getSqlMapClientTemplate().update("mi.update", dto);
	}

	public void delete(SawonDto dto) {
		getSqlMapClientTemplate().delete("mi.delete", dto);
	}

	public void refresh(SawonDto dto) {
		getSqlMapClientTemplate().insert("mi.insert", dto);
		getSqlMapClientTemplate().update("mi.update", dto);
		getSqlMapClientTemplate().delete("mi.delete", dto);
	}
}
