package com.Jorm.Dal.Ecampls;

import com.Jorm.Dal.Mysql.IEntity;
import com.Jorm.Dal.Mysql.Jorm;

public class Customers implements IEntity {

	public int Id_pm; // _pm prefix is for define Primary Key

	public String customerName_cn; // _cn prefix is for define Column Name

	public String customerPhone_cn;

	public String customerAddress_cn;

	public Sales customerId_cn;// mapping to sales object // sales.customerId_cn = customer.Id_pm

	public Jorm Jorm = new Jorm(this);

}
