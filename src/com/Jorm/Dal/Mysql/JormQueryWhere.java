package com.Jorm.Dal.Mysql;

import java.util.ArrayList;

public class JormQueryWhere {
	private IEntity JormObject;
	private MysqlDatabaseEngine dbEngine;
	private String lastQuery = null;
	private IEntity[] myentities;
	private IQuery myiquery;

	public void setIEntity(IEntity[] e) {
		this.myentities = e;
	}

	public IEntity[] getIEntitiy() {
		return this.getIEntitiy();
	}

	public void setIQueryObject(IQuery i) {
		this.myiquery = i;
	}

	public IQuery getIQueryObject() {
		return this.myiquery;
	}

	public JormQueryWhere(IEntity obj) {
		this.JormObject = obj;
	}

	public void setLastQuery(String lastQuery) {
		this.lastQuery = lastQuery;
	}

	public ArrayList<IQuery> Equal(Object value) {

		this.lastQuery += " = '" + value.toString() + "')";

		dbEngine = new MysqlDatabaseEngine(new IEntity() {
		});

		return dbEngine.runQuery(this.lastQuery, this.myiquery, this.myentities);

	}

	public ArrayList<IQuery> NotEqual(Object value) {

		this.lastQuery += " <> '" + value.toString() + "')";

		dbEngine = new MysqlDatabaseEngine(new IEntity() {
		});

		return dbEngine.runQuery(this.lastQuery, this.myiquery, this.myentities);

	}

	public ArrayList<IQuery> GreaterThan(Object value) {

		this.lastQuery += " > '" + value.toString() + "')";

		dbEngine = new MysqlDatabaseEngine(new IEntity() {
		});

		return dbEngine.runQuery(this.lastQuery, this.myiquery, this.myentities);

	}

	public ArrayList<IQuery> LessThan(Object value) {

		this.lastQuery += " < '" + value.toString() + "')";

		dbEngine = new MysqlDatabaseEngine(new IEntity() {
		});

		return dbEngine.runQuery(this.lastQuery, this.myiquery, this.myentities);

	}

	public ArrayList<IQuery> GreaterThanOrEqualtO(Object value) {

		this.lastQuery += " >= '" + value.toString() + "')";

		dbEngine = new MysqlDatabaseEngine(new IEntity() {
		});

		return dbEngine.runQuery(this.lastQuery, this.myiquery, this.myentities);
	}

	public ArrayList<IQuery> LessThanOrEqualTo(Object value) {

		this.lastQuery += " <= '" + value.toString() + "')";

		dbEngine = new MysqlDatabaseEngine(new IEntity() {
		});

		return dbEngine.runQuery(this.lastQuery, this.myiquery, this.myentities);

	}

	public ArrayList<IQuery> LIKE(Object value) {

		this.lastQuery += " LIKE  '" + value.toString() + "')";

		dbEngine = new MysqlDatabaseEngine(new IEntity() {
		});

		return dbEngine.runQuery(this.lastQuery, this.myiquery, this.myentities);
	}

}
