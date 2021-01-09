package com.Jorm.Dal.Mysql;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.net.ssl.SSLContext;

public class JormQueryDal {

	public static final Enum Table = null;

	private IEntity iQueryObject;

	private String queryString = "";

	private IQuery myQueryObject;

	private String tableName = "";

	private IEntity[] myentitys;

	// public Field addStatament;

	private MysqlDatabaseEngine dbEngine;

	public String getQueryString() {

		return queryString;
	}

	public JormQueryDal(IQuery iq, IEntity... entity) {

		this.myentitys = entity;
		this.myQueryObject = iq;

		ArrayList<String> entities = new ArrayList<String>();
		JormGetFields jrmFields;
		String allFields = "";

		// System.out.println(iq.getClass().newInstance());

		for (IEntity xyentity : entity) {

			entities.add(xyentity.getClass().getSimpleName().toLowerCase()); // Get table names by class names

			this.tableName += xyentity.getClass().getSimpleName().toLowerCase() + " as "
					+ xyentity.getClass().getSimpleName().toLowerCase() + ",";

			jrmFields = new JormGetFields(xyentity);

			for (Object[] entityField : jrmFields.getFields()) {
				allFields += xyentity.getClass().getSimpleName().toLowerCase() + "." + entityField[1] + " as "
						+ xyentity.getClass().getSimpleName().toLowerCase() + "_" + entityField[1] + ",";
			}

		}

		allFields = (allFields.substring(0, allFields.length() - 1));

		this.queryString = "select " + allFields + " from " + this.tableName.substring(0, this.tableName.length() - 1)
				+ " where "; // first query string

		String primaryKey = "";

		for (IEntity myentity : entity) {

			Field[] myfield = myentity.getClass().getDeclaredFields();

			for (Field currentField : myfield) {

				currentField.setAccessible(true);

				if (currentField.getName().endsWith("_pm"))
					primaryKey = currentField.getName();

				if (entities.contains(currentField.getType().getSimpleName().toLowerCase())) {

					if (checkEntity(currentField)) {

						queryString += currentField.getDeclaringClass().getSimpleName().toLowerCase() + "." + primaryKey
								+ "=";

						queryString += currentField.getType().getSimpleName().toLowerCase() + "."
								+ currentField.getName() + " AND ";

					}

				}
			}

		}

		this.queryString = this.queryString.substring(0, queryString.length() - 5);

	}

	private boolean checkEntity(Field field) { // Check a field has an IEntity implements

		boolean result = false;

		for (int i = 0; i < field.getType().getInterfaces().length; i++) {

			if (field.getType().getInterfaces()[i].getTypeName().contains("IEntity")) {
				result = true;
			}
		}

		return result;
	}

	public JormQueryWhere addStatament(IEntity f, String str) {

		JormQueryWhere ds = new JormQueryWhere(this.iQueryObject);
		ds.setIEntity(this.myentitys);
		ds.setIQueryObject(this.myQueryObject);
		ds.setLastQuery(this.queryString + " AND (" + f.getClass().getSimpleName().toLowerCase() + "." + str);

		return ds;

		// return Table;
	}

	public ArrayList<IQuery> SelectAll() {

		dbEngine = new MysqlDatabaseEngine(new IEntity() {
		});

		return dbEngine.runQuery(this.getQueryString(), this.myQueryObject, this.myentitys);
	}

}
