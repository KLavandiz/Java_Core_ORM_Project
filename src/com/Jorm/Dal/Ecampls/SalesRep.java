package com.Jorm.Dal.Ecampls;

import com.Jorm.Dal.Mysql.IEntity;
import com.Jorm.Dal.Mysql.Jorm;

public class SalesRep implements IEntity {

	public int Id_pm; // _pm prefix for Primary Key

	public String salesRepName_cn; // _cn prefix is for Column Name

	public String salesRepPhone_cn;

	public String salesEmail_cn;

	public String salesAddress_cn;

	public Sales salesRepId_cn;// Mapping done ! join to Sales object - sales.salesRepId_cn = SalesRep.Id_pm

	public Jorm Jorm = new Jorm(this); // object registered to the Java Object Relating Mapping

}
