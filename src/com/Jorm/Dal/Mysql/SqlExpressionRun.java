package com.Jorm.Dal.Mysql;

import java.util.ArrayList;

public class SqlExpressionRun {

	private IEntity JormObject;
	private MysqlDatabaseEngine dbEngine;
	private String lastQuery = null;

	public SqlExpressionRun(IEntity obj) {
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

}
