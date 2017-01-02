package com.jlab.education.dto;

import java.io.Serializable;
import java.util.*;

import com.tobesoft.platform.data.*;

public class UdDTO implements Serializable {
	
	private static final long serialVersionUID = 5374424584879470680L;

	private Map<String, String> variableList;

	@SuppressWarnings("rawtypes")
	private Map<String, Map> dataSetList;

	private Map<?, ?> Objects;

	public void setVariableList(Map<String, String> variableList) {
		this.variableList = variableList;
	}

	public void setDataSetList(Map<?, ?> dataSetList) {
		//this.dataSetList = dataSetList;
	}

	public Map<String, String> getVariableList() {
		return variableList;
	}

	@SuppressWarnings("rawtypes")
	public Map<String, Map> getDataSetList() {
		return dataSetList;
	}

	public void setObjects(Map<?, ?> objects) {
		Objects = objects;
	}

	public Map<?, ?> getObjects() {
		return Objects;
	}

	public void setVariableListToMap(VariableList vList) {
		variableList = new HashMap<String, String>();

		for (int i = 0; i < vList.size(); i++) {
			variableList.put(vList.get(i).getId(), vList.get(i).getValue().getString());
		}
	}

	public void setDataSetListToMap(DatasetList dataSetList) {
		List<Object> list = new ArrayList<Object>();
		java.util.Map<String, String> hm = new HashMap<String, String>();

		Dataset ds_input = dataSetList.get("ds_input");
		// insert, update처리
		for (int i = 0; i < ds_input.getRowCount(); i++) {
			if ("update".equals(ds_input.getRowStatus(i))) {
				hm = new HashMap<String, String>();
				for (int j = 0; j < ds_input.getColumnCount(); j++) {
					hm.put(ds_input.getColumnId(j), ds_input.getColumnAsString(i, j));
				}
			} else if ("insert".equals(ds_input.getRowStatus(i))) {
				hm = new HashMap<String, String>();
				for (int j = 0; j < ds_input.getColumnCount(); j++) {
					hm.put(ds_input.getColumnId(j), ds_input.getColumnAsString(i, j));
				}
			}
			list.add(hm);
		}
		this.dataSetList.put("ds_input", hm);
	}
}
