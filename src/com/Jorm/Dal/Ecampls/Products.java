package com.Jorm.Dal.Ecampls;

import com.Jorm.Dal.Mysql.IEntity;
import com.Jorm.Dal.Mysql.Jorm;

public class Products implements IEntity {

	public int Id_pm; // _pm prefix is for Primary Key

	public String productCode_cn; // _cn prefix is Column Name

	public String productName_cn;

	public double salesPrice_cn;

	public Sales productId_cn; // Mapping to sales object sales.productId_cn = Products.Id_pm

	public Jorm Jorm = new Jorm(this);

}
