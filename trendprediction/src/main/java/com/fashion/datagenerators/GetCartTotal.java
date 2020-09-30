package com.fashion.datagenerators;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.fashion.services.DBConnector;

public class GetCartTotal 
{
	private double total;
	private double discount;
	private double grand;
	private int tax;
	
	public void showCartTotal(int orderno)
	{		
		try
		{
			Connection con=DBConnector.connectDB();
			Statement st=con.createStatement();
						
			ResultSet rs=st.executeQuery("select sum(total) 'total' from shoppingcart where orderno="+orderno);
			
			while(rs.next())
			{								
				total=Math.round(rs.getDouble("total"));				
				System.out.println(total);
			}
			
			if(total==0)
				tax=0;
			else
				tax=150;
			
			discount=Math.round((10*total)/100);
			grand=total+tax-discount;
			
		}
		catch(Exception ex) {System.out.println("Error : "+ex);}
		
	}


	public int getTax() {
		return tax;
	}


	public void setTax(int tax) {
		this.tax = tax;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public double getDiscount() {
		return discount;
	}


	public void setDiscount(double discount) {
		this.discount = discount;
	}


	public double getGrand() {
		return grand;
	}


	public void setGrand(double grand) {
		this.grand = grand;
	}

	
	
}
