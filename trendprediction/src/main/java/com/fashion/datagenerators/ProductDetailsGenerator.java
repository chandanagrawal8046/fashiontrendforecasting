package com.fashion.datagenerators;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import com.fashion.services.DBConnector;
import com.fashion.services.PredictionFuns;

public class ProductDetailsGenerator 
{
	private int id;
	private String title;
	private String categories;
	private String description;
	private double price;
	private String imgurl;
	private String brand;
	private String alsoviewed;
	private String asinno;
	private int stock;
	private String status;
	private int wishcount;

	public ProductDetailsGenerator(int prodid)
	{
		try
		{			
			Connection con=DBConnector.connectDB();
			Statement st=con.createStatement();
						
			ResultSet rs=st.executeQuery("select * from products where id="+prodid+" and status='active'");
			
			
			while(rs.next())
			{								
				id=rs.getInt("id");
				title=rs.getString("title");
				description=rs.getString("pdescription");
				asinno=rs.getString("asinNo");				
				imgurl=rs.getString("imgurl");
				brand=rs.getString("brand");				
				alsoviewed=rs.getString("alsoviewed");
				stock=rs.getInt("stock");
				status=rs.getString("status");
				price=rs.getDouble("price");
				categories=rs.getString("categories");
				wishcount=getWishCount(rs.getInt("id"));
				
				System.out.println(rs.getInt("id")+"  "+rs.getString("title")+"		"+rs.getString("categories"));
			}			
		}
		catch(Exception ex) {System.out.println("Error List : "+ex);}
	}
	
	private int getWishCount(int prodid)
	{
		int wishcount=0;
		try
		{
			Vector<String> vct=new Vector<String>();
			PredictionFuns pf=new PredictionFuns();
			
			vct=pf.getValue("select count(*) 'wishhits' from wishlist where prodid="+prodid, 1);
			if(vct.size()>0)
				wishcount=Integer.parseInt(vct.elementAt(0));
			
		}
		catch(Exception ex) {}
		return wishcount;
	}

	public int getWishcount() {
		return wishcount;
	}

	public void setWishcount(int wishcount) {
		this.wishcount = wishcount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getAlsoviewed() {
		return alsoviewed;
	}

	public void setAlsoviewed(String alsoviewed) {
		this.alsoviewed = alsoviewed;
	}

	public String getAsinno() {
		return asinno;
	}

	public void setAsinno(String asinno) {
		this.asinno = asinno;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
