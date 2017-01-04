package com.jlab.education.mi;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;

import com.jlab.education.dto.SawonDto;
import com.tobesoft.platform.data.*;

public class MiDTO implements Serializable {
	
	private static final long serialVersionUID = 5374424584879470680L;

	private Map<String, String> variableListMap;
	public Map<String, String> getVList() { return variableListMap; }

	/**
	 * dataSetListMap = Map<DS¸íÄª, DS¸Ê>
	 * ¦¦ DS¸Ê = Map<ºÐ·ù(C, R, U, D), CRUD¸®½ºÆ®>
	 * ¡¡¦¦ CRUD¸®½ºÆ® = List<Column, Value>
	 */
	@SuppressWarnings("rawtypes")
	private Map<String, Map> dataSetListMap;
	@SuppressWarnings("rawtypes")
	public Map<String, Map> getDSList() { return dataSetListMap; }

	/** VariableList */

	private VariableList variableList;
	
	public VariableList getVariableList() {
		variableList.clear();
		for (String key : getVList().keySet()) {
			variableList.addVariable(key, get(key));
		}
		return variableList;
	}

	public String get(String name) {
		if (variableListMap != null) {
			return variableListMap.get(name);
		} else {
			return null;
		}
	}
	
	public void setVariableList(VariableList vList) {
		this.variableList = vList;
		
		variableListMap = new HashMap<String, String>();
			for (int i = 0; i < vList.size(); i++) {
				variableListMap.put(
					vList.getVariable(i).getID(),
					vList.getVariable(i).getValue().getString()
				);
			}
		
	}

	/** DatasetList */
	
	private DatasetList datasetList;

	public DatasetList getDatasetList(Class<?> voCls) {
		datasetList.clear();
		for (String dsName : getDSList().keySet()) {
			Map curDS = getDSList().get(dsName);
			
			for (Object dsMapName : curDS.keySet()) {
				Map dsMap = (Map) curDS.get(dsMapName);
				List list = (List) dsMap.get("select");
				Dataset ds = Mi.list2ds(dsName, list);
				datasetList.addDataset(ds);
			}
		}
		return datasetList;
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setDatasetList(DatasetList dsList) {
		this.datasetList = dsList;

		Map rst = new HashMap();
		for (int dsCount=0; dsCount<dsList.size(); dsCount++) {
			HashMap crudMap = new HashMap();
			
			List insert = new ArrayList();
			List select = new ArrayList();
			List update = new ArrayList();
			List delete = new ArrayList();
			
			Map<String, String> hm;
			
			Dataset ds = (Dataset) dsList.getDataset(dsCount);
			for (int i = 0; i < ds.getRowCount(); i++) {
				hm = new HashMap<String, String>();
				for (int j = 0; j < ds.getColumnCount(); j++) {
					hm.put(ds.getColumnID(j), ds.getColumnAsString(i, j));
				}
				if ("update".equals(ds.getRowStatus(i))) {
					update.add(hm);
				} else if ("insert".equals(ds.getRowStatus(i))) {
					insert.add(hm);
				} else {
					select.add(hm);
				}
			}

			for (int i = 0; i < ds.getDeleteRowCount(); i++) {
				hm = new HashMap<String, String>();
				for (int j = 0; j < ds.getColumnCount(); j++) {
					String value = ds.getDeleteColumn(i, ds.getColumnID(j)).getString();
					hm.put(ds.getColumnID(j), value);
				}
				delete.add(hm);
			}
			crudMap.put("insert", insert);
			crudMap.put("select", select);
			crudMap.put("update", update);
			crudMap.put("delete", delete);
			
			rst.put(ds.getDataSetID(), crudMap);
			
			this.dataSetListMap = rst;
		}
	}
	
	public List getInsert(String dsName) {
		return ((List) getDSList().get(dsName).get("insert"));
	}
	public List getSelect(String dsName) {
		return ((List) getDSList().get(dsName).get("select"));
	}
	public List getUpdate(String dsName) {
		return ((List) getDSList().get(dsName).get("update"));
	}
	public List getDelete(String dsName) {
		return ((List) getDSList().get(dsName).get("delete"));
	}
	
	@Override
	public String toString() {
		return "MiDTO [variableListMap=" + variableListMap + ", dataSetListMap=" + dataSetListMap + ", variableList="
				+ variableList + ", datasetList=" + datasetList + "]";
	}
}
