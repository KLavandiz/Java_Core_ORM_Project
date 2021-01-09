package com.Jorm.Dal.Ecampls;

import com.Jorm.Dal.Mysql.IEntity;
import com.Jorm.Dal.Mysql.Jorm;

public class Sales implements IEntity {

	public long Id_pm; // _pm prefix is for Primary Key in table

	public long salesRepId_cn; // _cn prefix is for Column Name

	public long customerId_cn;

	public long productId_cn;

	public double salesAmount_cn;

	public double balance_cn;

	public String date_cn;

	public Jorm Jorm = new Jorm(this); // object fields has been defined an registered to the Jorm

}
