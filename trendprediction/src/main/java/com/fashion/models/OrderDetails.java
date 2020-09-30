package com.fashion.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.fashion.services.DBConnector;

public class OrderDetails 
{
	private int orderno;
	private String userid;
	private String orderdt;
	private double bill;
	private String paymode;
	private String paystatus;
	private String orderstatus;
	private String name;
	private int prodid;
	private String title;
	private int quantity;
	private String imgurl;
	private double price;
	private double total;
	
	public OrderDetails()
	{
		orderno=0;
		userid="";
		orderdt="";
		bill=0;
		paymode="";
		paystatus="";
		orderstatus="";
		name="";
		prodid=0;
		title="";
		quantity=0;
		imgurl="";
		price=0;
		total=0;	
	}
	
	public ArrayList<OrderDetails> getOrderDetails()
	{
		ArrayList<OrderDetails> obj=new ArrayList<OrderDetails>();
		
		try
		{			
			Connection con=DBConnector.connectDB();
			Statement st=con.createStatement();
			
			String qr="";
			
			qr="select * from orderdetails where orderno="+orderno;
						
			ResultSet rs=st.executeQuery(qr);
			
			OrderDetails c;
			while(rs.next())
			{		

				c=new OrderDetails();
				c.setOrderno(rs.getInt("orderno"));
				c.setUserid(rs.getString("userid"));
				c.setName(rs.getString("name"));				
				c.setOrderdt(rs.getString("orderdt"));
				c.setBill(rs.getDouble("bill"));
				c.setPaymode(rs.getString("paymode"));
				c.setPaystatus(rs.getString("paystatus"));
			
				
				c.setProdid(rs.getInt("prodid"));
				c.setTitle(rs.getString("title"));
				c.setQuantity(rs.getInt("quantity"));				
				c.setImgurl(rs.getString("imgurl"));
				c.setPrice(Math.round(rs.getDouble("price")));
				c.setTotal(Math.round(rs.getDouble("total")));
				
				obj.add(c);
				System.out.println(rs.getInt("orderno")+"  "+rs.getString("name")+"		"+rs.getString("orderdt")+"		"+rs.getString("title"));
			}			
		}
		catch(Exception ex) {System.out.println("Error List : "+ex);}
		return obj;
	}

	public int getOrderno() {
		return orderno;
	}

	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOrderdt() {
		return orderdt;
	}

	public void setOrderdt(String orderdt) {
		this.orderdt = orderdt;
	}

	public double getBill() {
		return bill;
	}

	public void setBill(double bill) {
		this.bill = bill;
	}

	public String getPaymode() {
		return paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}

	public String getPaystatus() {
		return paystatus;
	}

	public void setPaystatus(String paystatus) {
		this.paystatus = paystatus;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProdid() {
		return prodid;
	}

	public void setProdid(int prodid) {
		this.prodid = prodid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	

}
