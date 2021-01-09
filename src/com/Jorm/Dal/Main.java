package com.Jorm.Dal;

import java.util.ArrayList;
import com.Jorm.Dal.Ecampls.*;
import com.Jorm.Dal.Mysql.*;



public class Main {
	/// just before try change mysql connection string on MysqlDatabaseEngine.java
	
	public static void main(String[] args) {

		
	Customers aCustomer = new Customers();	// its an IEntity
	aCustomer.Jorm.CreateTable(); // its create database table if the table is not exists in DB
	
//	aCustomer.customerName_cn="Kemal";
	
//	aCustomer.customerPhone_cn="07556190000";
	
//	aCustomer.customerAddress_cn="London - United Kingdom";
	
//	aCustomer.Jorm.Add();//new Customer object has been added.
	
//	aCustomer.Jorm.Update(); // current object will be updated.
	
	
	Products aProduct = new Products();
	aProduct.Jorm.CreateTable(); // create product table if isnot exists
	
	
	Sales aSales = new Sales();
	aSales.Jorm.CreateTable();
	
	
	SalesRep aSalesRep = new SalesRep();
	aSales.Jorm.CreateTable();
	
	
	
	
	ArrayList<IEntity> customerList;
	
	 customerList = 	aCustomer.Jorm.SelectAll().Run(); //gets All customer datas from table
	
	 customerList = 	aCustomer.Jorm.SelectAll().GroudBy("Id_pm").Run(); // Group by Id_pm
	 
	 customerList = 	aCustomer.Jorm.SelectAll().LIMIT(0, 10).Run(); //LIMIT
			 
	 customerList = 	aCustomer.Jorm.SelectAll().OrderBy("Id_pm", "ASC").Run(); //Order by 
	 
	 // simply check aCustomer.Jorm.... many more easy data formating without knowing sql
	 
	
	for(IEntity curretnCustomer:customerList) {
		
		
	//	((Customers)curretnCustomer).customerName_cn  converting to the correct type
		
	//	System.out.println(((Customers)curretnCustomer).customerName_cn); // its works!
		
	}
		


		

////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		AllSales allsales = new AllSales();  // 4 object mapping example

		ArrayList<IQuery> mysales = allsales.jormQuer.SelectAll();

		AllSales records; // reference class

		Sales sales;

		Products product;

		Customers customer;

		SalesRep salesrep;

		for (IQuery salesObject : mysales) {

			records = (AllSales) salesObject;

			sales = (Sales) records.sales;

			product = (Products) records.products;

			customer = (Customers) records.customer;

			salesrep = (SalesRep) records.salesrep;

			System.out.println("Sales ID : " + sales.Id_pm +" Customer Name: " + customer.customerName_cn );

		}
		
		records = null;
		
		sales = null;
		
		product = null;
		
		customer = null;
		
		salesrep = null;
		
		

	}

}
