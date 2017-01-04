package com.jlab.education.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jlab.education.dao.MiDao;
import com.jlab.education.dto.SawonDto;
import com.jlab.education.mi.Mi;
import com.jlab.education.mi.MiDTO;

@Service
@SuppressWarnings({"unchecked","rawtypes"})
public class MiService {

	@Autowired
	private MiDao dao;
	
	public List<SawonDto> findSawon(String name) {
		return dao.findSawon(name);
	}
	
	public Map<String, List<?>> getInitCode() {
		List<?> depts = dao.readAllDepts();
		List<?> jikgups = dao.readAllJikgups();
		
		System.out.println(depts);
		System.out.println(jikgups);
		
		Map<String, List<?>> rst = new HashMap<String, List<?>>();
		rst.put("ds_dept", depts);
		rst.put("ds_jikgup", jikgups);
		return rst;
	}

	@Transactional
	public void modifySawon(Map map) {
		//dao.insert(Mi.crud);
		((List) map.get("insert")).forEach(new Consumer() {
			@Override
			public void accept(Object m) {
				SawonDto cur = (SawonDto) Mi.map2vo((Map)m,  SawonDto.class);
				dao.insert(cur);
			}
		});
		((List) map.get("update")).forEach(new Consumer() {
			@Override
			public void accept(Object m) {
				SawonDto cur = (SawonDto) Mi.map2vo((Map)m,  SawonDto.class);
				dao.update(cur);
			}
		});
		((List) map.get("delete")).forEach(new Consumer() {
			@Override
			public void accept(Object m) {
				SawonDto cur = (SawonDto) Mi.map2vo((Map)m,  SawonDto.class);
				dao.delete(cur);
			}
		});
	}
/*
	public void modifySawon(MiDTO in) {
		String dsName = "input";
		in.getInsert(dsName).forEach(new Consumer() {
			@Override
			public void accept(Object m) {
				SawonDto cur = (SawonDto) Mi.map2vo((Map)m,  SawonDto.class);
				dao.insert(cur);
			}
		});
	}*/
}
