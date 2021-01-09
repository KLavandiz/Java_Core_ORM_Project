package com.Jorm.Dal.Mysql;

import com.Jorm.Dal.*;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.regex.Pattern;

//JormMysql.java is for Mysql sql querys
// we got the ITable item by the constructor injection 
// we will get all the declared fileds to create a table in DB

public class JormMysql {

	private IEntity JormObject; // ITable is the only allowed object to map Mysql Database.

	private String tableName = null;

	private JormGetFields JormFields;

	private String lastQuery = null;

	private ArrayList<Object[]> declaredFields = new ArrayList<Object[]>();

	private void setLastQuery(String lastQuery) {
		this.lastQuery = lastQuery;
	}

	private MysqlDatabaseEngine dbEngine;

	public JormMysql(IEntity Object) {

		this.JormObject = Object; // Constructor injection to get ITable object details to map DB.

		this.tableName = this.JormObject.getClass().getSimpleName().toLowerCase(); // we take class name for to be Table
																					// name.

	}

	public int Add() {

		String fieldNames = null;
		String fieldValues = null;
		String query = null;

		JormFields = new JormGetFields(this.JormObject); // get All declared fields in ITable object

		this.declaredFields = JormFields.getFields(); // get Declared Fields to create a table.

		for (Object[] field : this.declaredFields) { // declared fields are checked in the loop

			// if the declared filed type is exist in dataTyps array we will replace to
			// replaceDataTyps by the same index

			if (!(field[1].toString().endsWith("_pm"))) { // if the field has _pm prefix that will be primary key

				fieldNames += "," + field[1].toString(); // statament for insert into query
				fieldValues += ",'" + field[2].toString().replace("'", "\\'") + "'";// statament for insert into
																					// values() query
			}

		}

		fieldNames = "(" + fieldNames.substring(5, fieldNames.length()) + ")";

		fieldValues = "(" + fieldValues.substring(5, fieldValues.length()) + ")";

		query = "INSERT INTO " + tableName + " " + fieldNames + " VALUES  " + fieldValues;

		dbEngine = new MysqlDatabaseEngine(this.JormObject); // Connect Mysql

		return dbEngine.runUpdateQuery(query); // Add new record. insert into...

	}

	public int Update() {

		String fieldValues = "";
		String updateId = "";
		String updateIdValue = "";
		String query = "";
		int qResult = -1;

		JormFields = new JormGetFields(this.JormObject);

		this.declaredFields = JormFields.getFields();
		// get Declared Fields to create a table.

		for (Object[] field : this.declaredFields) {

			if (field[1].toString().endsWith("_pm")) {

				updateId = field[1].toString();
				updateIdValue = field[2].toString();

			} else if ((!field[2].toString().equals("0")) && (!field[2].toString().equals("0.0"))) {

				fieldValues += field[1].toString() + "=  '" + field[2].toString().replace("'", "\\'") + "',";
			}

		}

		query = "UPDATE " + tableName + " SET " + fieldValues.substring(0, fieldValues.length() - 1) + " WHERE  "
				+ updateId + "='" + updateIdValue + "'";

		dbEngine = new MysqlDatabaseEngine(this.JormObject); // Connect Mysql

		ArrayList<Object[]> checkAvailability = dbEngine.runExecuteCount(
				"select count(*) from " + "" + tableName + " where " + updateId + "='" + updateIdValue + "'");

		if (Integer.parseInt(checkAvailability.get(0)[0].toString()) == 0) {
			this.Add(); // if given id is not exists than add new record
			qResult = 0;
		} else {
			qResult = dbEngine.runUpdateQuery(query); // Update current record

		}

		return qResult;

	}

	public SqlExpression SelectAll() { // to get select * from table

		String lastQuery = null;

		lastQuery = "select * from " + this.tableName;

		SqlExpression ds = new SqlExpression(this.JormObject);
		ds.setLastQuery(lastQuery); // put query in the datasource(ds) object and make ready for Run() method
		return ds;

	}

	public SqlExpression SelectOnly(String... args) { // to get select * from table

		String lastQuery = null;
		String byone = "";
		for (String arg : args) {
			byone += arg + ",";
		}

		byone = byone.substring(0, byone.length() - 1);
		lastQuery = "select " + byone + " from " + this.tableName;

		SqlExpression ds = new SqlExpression(this.JormObject);
		ds.setLastQuery(lastQuery); // put query in the datasource(ds) object and make ready for Run() method
		return ds;

	}

	public int Count() {

		dbEngine = new MysqlDatabaseEngine(this.JormObject);
		ArrayList<Object[]> queryResult = dbEngine.runExecuteCount("select COUNT(*) from " + this.tableName); // run
																												// query
		return Integer.parseInt(queryResult.get(0)[0].toString());// get

	}

	public int CreateTable() {

		String sqlCreateTable = "CREATE TABLE IF NOT EXISTS " + this.tableName + " (";

		JormFields = new JormGetFields(this.JormObject);

		this.declaredFields = JormFields.getFields();

		for (Object[] field : this.declaredFields) {

			String type = findTheBestDataType(field[0].toString().toLowerCase());

			if (field[1].toString().endsWith("_pm")) {

				sqlCreateTable += " " + field[1].toString() + " int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,";
			} else {

				sqlCreateTable += " " + field[1].toString() + " " + type + " NOT NULL ,";
			}

		}

		sqlCreateTable = sqlCreateTable.substring(0, sqlCreateTable.length() - 1) + ")";

		dbEngine = new MysqlDatabaseEngine(this.JormObject);

		return dbEngine.runUpdateQuery(sqlCreateTable);

	}

	private String findTheBestDataType(String currentType) {

		String newType = "text";

		switch (currentType) {
		case "int":
			newType = "int";
			break;
		case "long":
			newType = "int";
			break;
		case "short":
			newType = "SMALLINT";
			break;
		case "byte":
			newType = "TINYINT";
			break;
		case "double":
			newType = "double";
			break;
		case "float":
			newType = "float";
			break;
		case "boolean":
			newType = "text";
			break;
		default:
			newType = "text";

		}

		return newType;
	}

}
