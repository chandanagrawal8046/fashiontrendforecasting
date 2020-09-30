package com.fashion.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.fashion.services.DBConnector;

public class CartDetails 
{
	private int id;
	private int orderno;
	private int prodid;
	private String title;
	private int quantity;
	private String imgurl;
	private double price;
	private double total;
	private int cartid;
	
	public CartDetails()
	{
		id=0;
		orderno=0;
		prodid=0;
		title="";
		quantity=0;
		imgurl="";
		price=0;
		total=0;
		cartid=0;
	}
	
	public int getCartid() {
		return cartid;
	}

	public void setCartid(int cartid) {
		this.cartid = cartid;
	}

	public ArrayList<CartDetails> getCartDetails()
	{
		ArrayList<CartDetails> lst=new ArrayList<CartDetails>();
	
		try
		{
			String qr="select * from cartdetails where orderno="+orderno;
	        System.out.println(qr);
	        Connection con=DBConnector.connectDB();        
	        Statement st=con.createStatement();
	        ResultSet rs=st.executeQuery(qr);
	        
	        CartDetails obj;
	        while(rs.next())
	        {
	            obj=new CartDetails();
	            System.out.println(rs.getString("title"));	            
	            obj.setProdid(rs.getInt("prodid"));
	            obj.setOrderno(rs.getInt("orderno"));
	            obj.setTitle(rs.getString("title"));
	            obj.setImgurl(rs.getString("imgurl"));
	            obj.setId(rs.getInt("id"));
	            obj.setQuantity(rs.getInt("quantity"));
	            obj.setPrice(rs.getDouble("price"));
	            obj.setTotal(rs.getDouble("total"));
	            lst.add(obj);  
	        }
        }
		catch(Exception ex) {}
		return lst;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderno() {
		return orderno;
	}

	public void setOrderno(int orderno) {
		this.orderno = orderno;
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
