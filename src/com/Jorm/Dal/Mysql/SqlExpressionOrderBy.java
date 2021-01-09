package com.Jorm.Dal.Mysql;

import java.util.ArrayList;

public class SqlExpressionOrderBy {

	private IEntity JormObject;
	private MysqlDatabaseEngine dbEngine;
	private String lastQuery = null;

	public SqlExpressionOrderBy(IEntity obj) {
		this.JormObject = obj;
	}

	public void setLastQuery(String lastQuery) {
		this.lastQuery = lastQuery;
	}

	public SqlExpressionRun LIMIT(int start, int finish) {

		SqlExpressionRun ds = new SqlExpressionRun(this.JormObject);
		ds.setLastQuery(this.lastQuery + " LIMIT" + " " + start + ", " + finish);
		return ds;
	}

	public ArrayList<IEntity> Run() {
		dbEngine = new MysqlDatabaseEngine(this.JormObject);
		ArrayList<IEntity> deneme = dbEngine.runExecuteQuery(this.lastQuery);
		this.lastQuery = null;
		return deneme;
	}

}
