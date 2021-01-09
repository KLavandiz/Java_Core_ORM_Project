package com.Jorm.Dal.Mysql;

public class SqlExpressionWhere {

	private IEntity JormObject;
	private MysqlDatabaseEngine dbEngine;
	private String lastQuery = null;

	public SqlExpressionWhere(IEntity obj) {
		this.JormObject = obj;
	}

	public void setLastQuery(String lastQuery) {
		this.lastQuery = lastQuery;
	}

	public SqlCondition Equal(Object value) {

		SqlCondition ds = new SqlCondition(this.JormObject);
		ds.setLastQuery(this.lastQuery + " = '" + value.toString() + "')");

		return ds;

	}

	public SqlCondition NotEqual(Object value) {

		SqlCondition ds = new SqlCondition(this.JormObject);
		ds.setLastQuery(this.lastQuery + " <> '" + value.toString() + "')");

		return ds;

	}

	public SqlCondition GreaterThan(Object value) {

		SqlCondition ds = new SqlCondition(this.JormObject);
		ds.setLastQuery(this.lastQuery + " > '" + value.toString() + "')");
		return ds;

	}

	public SqlCondition LessThan(Object value) {

		SqlCondition ds = new SqlCondition(this.JormObject);
		ds.setLastQuery(this.lastQuery + " < '" + value.toString() + "')");
		return ds;

	}

	public SqlCondition GreaterThanOrEqualtO(Object value) {

		SqlCondition ds = new SqlCondition(this.JormObject);
		ds.setLastQuery(this.lastQuery + " >= '" + value.toString() + "')");
		return ds;

	}

	public SqlCondition LessThanOrEqualTo(Object value) {

		SqlCondition ds = new SqlCondition(this.JormObject);
		ds.setLastQuery(this.lastQuery + " <= '" + value.toString() + "')");
		return ds;

	}

	public SqlCondition LIKE(Object value) {

		SqlCondition ds = new SqlCondition(this.JormObject);
		ds.setLastQuery(this.lastQuery + " LIKE  '" + value.toString() + "')");
		return ds;

	}

}
