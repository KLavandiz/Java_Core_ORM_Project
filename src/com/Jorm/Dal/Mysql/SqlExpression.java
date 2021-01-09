package com.Jorm.Dal.Mysql;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class SqlExpression {

	private IEntity JormObject;
	private MysqlDatabaseEngine dbEngine;
	private String lastQuery = null;
	private JormGetFields JormFields;
	private ArrayList<Object[]> declaredFields = new ArrayList<Object[]>();

	public SqlExpression(IEntity obj) {
		this.JormObject = obj;
	}

	public void setLastQuery(String lastQuery) {
		this.lastQuery = lastQuery;
	}

	public SqlExpressionWhere WHERE(String fieldName) {

		SqlExpressionWhere ds = new SqlExpressionWhere(this.JormObject);
		ds.setLastQuery(this.lastQuery + " WHERE" + " (" + fieldName);
		return ds;

	}

	public SqlExpressionOrderBy OrderBy(String field, String desc) {

		SqlExpressionOrderBy ds = new SqlExpressionOrderBy(this.JormObject);
		ds.setLastQuery(this.lastQuery + " ORDER BY" + " " + field + " " + desc);
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

	/*
	 * public SqlExpression IN(ITable obj) {
	 * 
	 * String innerJoin="";
	 * innerJoin+="inner join "+obj.getClass().getSimpleName().toLowerCase()+" on "
	 * +this.JormObject.getClass().getSimpleName().toLowerCase()+"."+
	 * getInnerJoin(obj.getClass().getSimpleName()).split("=")[1]+"="+obj.getClass()
	 * .getSimpleName().split("=")[0].toLowerCase()+"."+
	 * getInnerJoin(obj.getClass().getSimpleName()).split("=")[0].replace("_jn","");
	 * 
	 * 
	 * 
	 * SqlExpression ds = new SqlExpression(this.JormObject);
	 * ds.setLastQuery(this.lastQuery + " " +innerJoin);
	 * System.out.println(this.lastQuery + " " +innerJoin);
	 * 
	 * return ds;
	 * 
	 * }
	 * 
	 */

	public ArrayList<IEntity> Run() {

		// type.cast(type)

		dbEngine = new MysqlDatabaseEngine(this.JormObject);

		ArrayList<IEntity> queryResult = dbEngine.runExecuteQuery(this.lastQuery);

		this.lastQuery = null;

		return queryResult;

	}

	/*
	 * public String getInnerJoin(String str){
	 * 
	 * 
	 * Field[] field = this.JormObject.getClass().getDeclaredFields(); // get
	 * declared fields.
	 * 
	 * 
	 * 
	 * String fieldDetails=""; String primaryKey="";
	 * 
	 * for(Field currentField:field) {
	 * 
	 * 
	 * currentField.setAccessible(true);
	 * 
	 * 
	 * if((currentField.getName().endsWith("_jn"))) {
	 * 
	 * 
	 * if(currentField.getType().getSimpleName().toString()==str){ fieldDetails =
	 * currentField.getName(); }
	 * 
	 * }
	 * 
	 * 
	 * if((currentField.getName().endsWith("_pm"))) {
	 * 
	 * primaryKey = currentField.getName();
	 * 
	 * }
	 * 
	 * 
	 * }
	 * 
	 * 
	 * return fieldDetails+"="+primaryKey; }
	 */
}
