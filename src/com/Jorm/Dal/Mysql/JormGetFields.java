package com.Jorm.Dal.Mysql;

import java.lang.reflect.Field;
import java.util.ArrayList;

// to get declared fields of the class with Jorm jorm= new Jorm();
public class JormGetFields {

	private IEntity JormObject;

	private String fieldTyps;

	private String fieldNames;

	private String fieldValues;

	private ArrayList<Object[]> FieldObjects = new ArrayList<Object[]>();

	public JormGetFields(IEntity Object) {
		this.JormObject = Object; // got the ITable object as constructor injection to get declared field names
	}

	public ArrayList<Object[]> getFields() {

		Field[] field = this.JormObject.getClass().getDeclaredFields(); // get declared fields.

		String[] fieldDetails;

		for (Field currentField : field) {

			currentField.setAccessible(true); // set setAccessible of fields true to get Private fields too

			if (((currentField.getName().endsWith("_cn")) || (currentField.getName().endsWith("_pm")))
					&& (!checkEntity(currentField))) { // only get prefixed fields

				fieldDetails = new String[3]; // item 0 is for Type of field, item 1 is name of field , item 2 is the
												// value of field

				fieldDetails[0] = currentField.getType().getSimpleName().toString(); // get Field Type
				fieldDetails[1] = currentField.getName(); // get Field Name
				try {
					if ((currentField.get(this.JormObject) == null)) { // if the field is null set field value as 0
						fieldDetails[2] = "0";
					} else {

						fieldDetails[2] = currentField.get(this.JormObject).toString(); // if hte field has a value put
																						// the string array index by 2
					}

				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}

				FieldObjects.add(fieldDetails); // put the string array to the Arraylist as an item

			}
			fieldDetails = null; // make sure on each loop zero the String array

		}

		return FieldObjects; // return object array as ArrayList
	}

	public boolean checkEntity(Field field) {
		boolean result = false;

		for (int i = 0; i < field.getType().getInterfaces().length; i++) {

			if (field.getType().getInterfaces()[i].getTypeName().contains("IEntity")) {
				result = true;
			}
		}

		return result;
	}

}
