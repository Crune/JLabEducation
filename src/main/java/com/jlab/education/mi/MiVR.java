package com.jlab.education.mi;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.view.AbstractView;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.tobesoft.platform.PlatformResponse;
import com.tobesoft.platform.data.ColumnInfo;
import com.tobesoft.platform.data.Dataset;
import com.tobesoft.platform.data.DatasetList;
import com.tobesoft.platform.data.PlatformData;
import com.tobesoft.platform.data.VariableList;

public class MiVR extends AbstractView {

	protected Log log = LogFactory.getLog(this.getClass());

	protected VariableList miVariableList = new VariableList();
	protected DatasetList miDatasetList = new DatasetList();

	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("stating viewing");

		PlatformData platformData = new PlatformData(miVariableList, miDatasetList);

		this.setMiResultMessage((String) model.get("MiResultCode"), (String) model.get("MiResultMsg"));

		Object vo = model.get("MiDTO");
		String dsName = (String) model.get("OutDsName");
		if (dsName == null) {
			dsName = "ds_output";
		}
		Dataset dataset = new Dataset(dsName);

		if (vo != null) {
			if (java.util.List.class.isAssignableFrom(vo.getClass())) {
				
				// 결과가 LIST 형태로 넘어왔을 경우
				System.out.println("vo is list");
				dataset = Mi.list2ds(dsName, (java.util.List) vo);
				miDatasetList.addDataset(dataset);
				
			} else if (java.util.Map.class.isAssignableFrom(vo.getClass())) {
				
				// 결과가 MAP 형태로 넘어왔을 경우
				System.out.println("vo is Map");
				Map<String, List> map = ((Map) vo);
				for (String key : map.keySet()) {
					dataset = Mi.list2ds(key, (java.util.List) map.get(key));
					miDatasetList.addDataset(dataset);
				}
				
			} else {
				
				// 결과가 VO 형태로 넘어왔을 경우
				System.out.println("vo is Object");
				dataset = Mi.vo2ds(dsName, vo);
				miDatasetList.addDataset(dataset);
				
			}
		}
		try {

			new PlatformResponse(response).sendData(platformData);

		} catch (IOException ex) {
			if (log.isErrorEnabled()) {
				log.error("Exception occurred while writing xml to MiPlatform Stream.", ex);
			}
			throw new Exception();
		}

	}
	
	public void setMiResultMessage(String code, String Msg) {
		miVariableList.addStr("ErrorCode", code);
		miVariableList.addStr("ErrorMsg", Msg);
	}

}
