package com.Jorm.Dal.Mysql;

import java.util.ArrayList;

public class SqlEcpressionGroupBy {

	private IEntity JormObject;
	private MysqlDatabaseEngine dbEngine;
	private String lastQuery = null;

	public SqlEcpressionGroupBy(IEntity obj) {
		this.JormObject = obj;
	}

	public void setLastQuery(String lastQuery) {
		this.lastQuery = lastQuery;
	}

	public ArrayList<IEntity> Run() {
		dbEngine = new MysqlDatabaseEngine(this.JormObject);
		ArrayList<IEntity> deneme = dbEngine.runExecuteQuery(this.lastQuery);
		this.lastQuery = null;
		return deneme;
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

}
