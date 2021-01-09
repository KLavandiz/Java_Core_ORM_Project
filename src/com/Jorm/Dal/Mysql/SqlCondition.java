package com.Jorm.Dal.Mysql;

import java.util.ArrayList;

public class SqlCondition {

	private IEntity JormObject;
	private MysqlDatabaseEngine dbEngine;
	private String lastQuery = null;

	public SqlCondition(IEntity obj) {
		this.JormObject = obj;
	}

	public void setLastQuery(String lastQuery) {
		this.lastQuery = lastQuery;
	}

	public SqlExpressionWhere AND(String fieldName) {
		SqlExpressionWhere ds = new SqlExpressionWhere(this.JormObject);

		ds.setLastQuery(this.lastQuery + " AND (" + fieldName);

		return ds;

	}

	public SqlExpressionWhere OR(String fieldName) {
		SqlExpressionWhere ds = new SqlExpressionWhere(this.JormObject);
		ds.setLastQuery(this.lastQuery + " OR (" + fieldName);

		return ds;

	}

	public SqlEcpressionGroupBy GroudBy(String FieldName) {

		SqlEcpressionGroupBy ds = new SqlEcpressionGroupBy(this.JormObject);
		ds.setLastQuery(this.lastQuery + " GROUP BY" + " " + FieldName);
		return ds;

	}

	public SqlExpressionRun LIMIT(int start, int finish) {

		SqlExpressionRun ds = new SqlExpressionRun(this.JormObject);
		ds.setLastQuery(this.lastQuery + " LIMIT" + " " + start + ", " + finish);
		return ds;
	}

	public SqlExpressionOrderBy OrderBy(String field, String desc) {

		SqlExpressionOrderBy ds = new SqlExpressionOrderBy(this.JormObject);
		ds.setLastQuery(this.lastQuery + " ORDER BY" + " " + field + " " + desc);
		return ds;
	}

	public ArrayList<IEntity> Run() {
		dbEngine = new MysqlDatabaseEngine(this.JormObject);
		ArrayList<IEntity> deneme = dbEngine.runExecuteQuery(this.lastQuery);
		this.lastQuery = null;
		return deneme;
	}

}
