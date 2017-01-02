package com.jlab.education.dao;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import java.sql.*;

import com.tobesoft.platform.*;
import com.tobesoft.platform.data.*;

public class MiDao {

	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;

	private static MiDao instance = new MiDao();

	public static MiDao getInstance() {
		return instance;
	}

	private MiDao() {}

	private static Connection getConnection() {
		
		if (conn == null) {
			/******* JDBC Connection *******/
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@118.219.52.29:1521:xe", "jlabsystem", "1234");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	/*********** �����Լ� *************/
	// ResultSet �� ��������, ��, "null"�� ""��
	public String rsGet(ResultSet rs, String id) throws Exception {
		if (rs.getString(id) == null)
			return "";
		else
			return rs.getString(id);
	}

	// �ѱ� ��ȸ���� ó���� �Լ�
	public static String enToko(String s) {
		String result = "";

		try {
			result = new String(s.getBytes("ISO-8859-1"), "EUC-KR");
		} catch (Exception e) {
			System.out.println(e);
		}

		return result;
	}

	VariableList vl = new VariableList();
	DatasetList dl = new DatasetList();

	public void test(String name, HttpServletResponse response) {
		try {
			stmt = getConnection().createStatement();
			
			/******* SQL ���� *************/
			String SQL;
			if (name.equals("") == true)
				SQL = "select * from base_sawon";
			else
				SQL = "select * from base_sawon where name like '" + enToko(name) + "%%'";
			rs = stmt.executeQuery(SQL);

			/********* Dataset ���� ************/
			Dataset ds = new Dataset("ds_sawon");
			ds.addColumn("name", ColumnInfo.CY_COLINFO_STRING, 20);
			ds.addColumn("sabun", ColumnInfo.CY_COLINFO_STRING, 5);
			ds.addColumn("dept", ColumnInfo.CY_COLINFO_STRING, 20);
			ds.addColumn("jikgup", ColumnInfo.CY_COLINFO_STRING, 20);
			ds.addColumn("sex", ColumnInfo.CY_COLINFO_STRING, 20);
			ds.addColumn("ipsa_date", ColumnInfo.CY_COLINFO_STRING, 20);
			ds.addColumn("marry", ColumnInfo.CY_COLINFO_STRING, 20);
			ds.addColumn("email", ColumnInfo.CY_COLINFO_STRING, 50);
			ds.addColumn("smemo", ColumnInfo.CY_COLINFO_STRING, 100);
			while (rs.next()) {
				int row = ds.appendRow();
				ds.setColumn(row, "name", rsGet(rs, "name"));
				ds.setColumn(row, "sabun", rsGet(rs, "sabun"));
				ds.setColumn(row, "dept", rsGet(rs, "dept"));
				ds.setColumn(row, "jikgup", rsGet(rs, "jikgup"));
				ds.setColumn(row, "sex", rsGet(rs, "sex"));
				ds.setColumn(row, "ipsa_date", rsGet(rs, "ipsa_date"));
				ds.setColumn(row, "marry", rsGet(rs, "marry"));
				ds.setColumn(row, "email", rsGet(rs, "email"));
				ds.setColumn(row, "smemo", rsGet(rs, "smemo"));
			}

			/********* ������ Dataset�� DatasetList�� �߰� ************/
			dl.addDataset(ds);

			/********* ������ VariableList�� �߰� ************/
			vl.addStr("ErrorCode", "0");
			vl.addStr("ErrorMsg", "SUCC");
		}
		/********* Erroró�� ************/
		catch (Exception e) {
			vl.addStr("ErrorCode", "-1");
			vl.addStr("ErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		
		/******** JDBC Close *******/
		if ( stmt != null ) try { stmt.close(); } catch (Exception e) {}
		if ( conn != null ) try { conn.close(); } catch (Exception e) {}
		
		/******** ��� XML ���� �� Web Server�� ���� ******/
		PlatformResponse pRes;
		try {
			pRes = new PlatformResponse(response, PlatformRequest.XML, "euc-kr");
			pRes.sendData(vl, dl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
