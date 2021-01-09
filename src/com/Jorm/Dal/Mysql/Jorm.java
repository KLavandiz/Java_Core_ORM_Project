package com.Jorm.Dal.Mysql;

import java.lang.reflect.Field;

/*
 * Jorm default database is MYSQL to change remove (extends JormMysql) statament from Jorm,java and type your new Database class as extends
 * 
 * 
 * */
public class Jorm extends JormMysql { // this bridge class extended from mysql configured Jorm it could change to the
										// Oracle Jorm

	public Jorm(IEntity Object) {
		super(Object);
	}

}
