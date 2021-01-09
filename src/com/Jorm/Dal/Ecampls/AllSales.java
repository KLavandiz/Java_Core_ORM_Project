package com.Jorm.Dal.Ecampls;

import com.Jorm.Dal.Mysql.*;

public class AllSales implements IQuery {

	// Mapped 4 objects

	public Sales sales = new Sales(); // sales object

	public Customers customer = new Customers();

	public Products products = new Products();

	public SalesRep salesrep = new SalesRep();

	public JormQuery jormQuer = new JormQuery(this, customer, products, salesrep, sales);

	// Mapped 4 object registered to the Jorm and query result will be an AllSales
	// type ArrayList . Each record will have 4 object

}
