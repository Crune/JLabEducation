<%@ page import="java.util.*"%>
<%@ page import="com.tobesoft.platform.*"%>
<%@ page import="com.tobesoft.platform.data.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>

<%@ page contentType="text/xml; charset=EUC-KR"%>

<%!/*********** �����Լ� *************/
	// DataSet �� ��������, ��, "null"�� ""��
	public String dsGet(Dataset ds, int rowno, String id) throws Exception {
		String value;
		value = ds.getColumn(rowno, id).getString();
		if (value == null)
			return "";
		else
			return value;
	}%>

<%
	/****** Service API �ʱ�ȭ ******/
	/** Input �κ� �ʱ�ȭ **/
	VariableList in_vl = new VariableList();
	DatasetList in_dl = new DatasetList();
	PlatformRequest pReq = new PlatformRequest(request, "euc-kr");

	/** Web Server���� XML���� �� Parsing **/
	pReq.receiveData();

	/** List ȹ�� �� Dataset, ���� ȹ�� **/
	in_vl = pReq.getVariableList();
	in_dl = pReq.getDatasetList();
	Dataset ds = in_dl.getDataset("input");
	String in_var1 = request.getParameter("in_var1");
	String in_var2 = in_vl.getValueAsString("in_var2");

	/** Output �κ� �ʱ�ȭ **/
	VariableList out_vl = new VariableList();
	DatasetList out_dl = new DatasetList();
%>

<%
	/******* JDBC Connection *******/
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	Class.forName("oracle.jdbc.driver.OracleDriver");
	conn = DriverManager.getConnection("jdbc:oracle:thin:@118.219.52.29:1521:xe", "jlabsystem", "1234");
	stmt = conn.createStatement();

	String SQL = "";
	int i;
	try {
		/******** Dataset�� INSERT, UPDATEó�� ********/
		for (i = 0; i < ds.getRowCount(); i++) {
			String row_status = ds.getRowStatus(i);
			if (row_status.equals("insert") == true) {
				// @formatter:off
				SQL = "insert into base_sawon ( name, sabun, dept, jikgup, sex, ipsa_date, marry, email, smemo ) values ( " +
					"'" + dsGet(ds,i,"name") + "'," + 
					"'" + dsGet(ds,i,"sabun") + "'," + 
					"'" + dsGet(ds,i,"dept") + "'," +  
					"'" + dsGet(ds,i,"jikgup") + "'," +
					"'" + dsGet(ds,i,"sex") + "'," +
					"'" + dsGet(ds,i,"ipsa_date") + "'," +
					"'" + dsGet(ds,i,"marry") + "'," +
					"'" + dsGet(ds,i,"email") + "'," +				
					"'" + dsGet(ds,i,"smemo") + "' )";
				// @formatter:on
			} else if (row_status.equals("update") == true) {
				String org_name = ds.getOrgColumn(i, "name").getString();
				// @formatter:off
				SQL = "update base_sawon set " +
					"name = '" + dsGet(ds,i,"name") + "'," + 
					"sabun = '" + dsGet(ds,i,"sabun") + "'," +
					"dept = '" + dsGet(ds,i,"dept") + "'," +  
					"jikgup = '" + dsGet(ds,i,"jikgup") + "'," +
					"sex = '" + dsGet(ds,i,"sex") + "'," +
					"ipsa_date = '" + dsGet(ds,i,"ipsa_date") + "'," +
					"marry = '" + dsGet(ds,i,"marry") + "'," +
					"email = '" + dsGet(ds,i,"email") + "'," +
					"smemo = '" + dsGet(ds,i,"smemo") + "' " +
					"where " +
					"name = " + "'" + org_name + "'";
				// @formatter:on
			}
			stmt.executeUpdate(SQL);
		}
		/****** Dataset�� DELETEó�� ****/
		for (i = 0; i < ds.getDeleteRowCount(); i++) {
			String name = ds.getDeleteColumn(i, "name").getString();
			// @formatter:off
			SQL = "delete from base_sawon where " +
			"name = " + "'" + name + "'";
			// @formatter:on
			stmt.executeUpdate(SQL);
		}

		/********* ������ VariableList�� �߰� ************/
		out_vl.addStr("ErrorCode", "0");
		out_vl.addStr("ErrorMsg", "SUCC");
		out_vl.addStr("out_var", in_var2);
	} catch (SQLException e) {
		out_vl.addStr("ErrorCode", "-1");
		out_vl.addStr("ErrorMsg", e.getMessage());
		out_vl.addStr("out_var", in_var2);
	}

	/******** JDBC Close ********/
	if (stmt != null)
		try {
			stmt.close();
		} catch (Exception e) {}
	if (conn != null)
		try {
			conn.close();
		} catch (Exception e) {}
%>

<%
	/******** ��?XML ���� �� Web Server�� ���� ******/
	out.clearBuffer();
	PlatformResponse pRes = new PlatformResponse(response, PlatformRequest.XML, "euc-kr");
	pRes.sendData(out_vl, out_dl);
%>