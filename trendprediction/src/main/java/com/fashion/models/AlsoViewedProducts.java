package com.fashion.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import com.fashion.services.DBConnector;
import com.fashion.services.PredictionFuns;

public class AlsoViewedProducts 
{
	
	private String userid;
	private int prodid;
	private String status;
	private String title;
	private String categories;
	private String description;
	private double price;
	private String imgurl;
	private String brand;	
	private int stock;
	
	
	public AlsoViewedProducts()
	{
		prodid=0;
		userid="";
		status="";
		title="";
		categories="";
		description="";
		price=0;
		imgurl="";
		brand="";
		stock=0;
		
				
	}
	
	public ArrayList<AlsoViewedProducts> getAlsoViewedReport()
	{
		ArrayList<AlsoViewedProducts> lst=new ArrayList<AlsoViewedProducts>();
		String qr="select distinct(prodid) 'prodid',p.title,p.pdescription,p.categories,p.price,p.imgurl,p.brand,p.stock,p.status";
		qr+=" from shoppingcart s,products p where p.id=s.prodid and s.prodid<>"+prodid+" and orderno in (";
				
		try
		{
			Vector<String> vct=new Vector<String>();
			PredictionFuns pf=new PredictionFuns();
			
			vct=pf.getValue("select orderno from shoppingcart where prodid="+prodid, 1);
			System.out.println("Vector Size : "+vct.size());
			if(vct.size()>0)
			{
				qr+=vct.elementAt(0);
				
				if(vct.size()>1)
				{
					for(int i=1;i<vct.size();i++)
					{
						qr+=","+vct.elementAt(i);
					}
				}
			}
			
			qr+=")";
			
			System.out.println("Query : "+qr);
			
			Connection con=DBConnector.connectDB();
			Statement st=con.createStatement();
			
			ResultSet rs=st.executeQuery(qr);		
			
			AlsoViewedProducts p;
			while(rs.next())
			{				
				p=new AlsoViewedProducts();
				
				p.setProdid(rs.getInt("prodid"));
				p.setTitle(rs.getString("title"));
				p.setDescription(rs.getString("pdescription"));
				p.setImgurl(rs.getString("imgurl"));
				p.setBrand(rs.getString("brand"));				
				p.setStock(rs.getInt("stock"));
				p.setStatus(rs.getString("status"));
				p.setPrice(rs.getDouble("price"));
				p.setCategories(rs.getString("categories"));
				
				lst.add(p);
				System.out.println(rs.getInt("prodid")+"  "+rs.getString("title")+"		"+rs.getString("categories"));
			}	
			
//			rs.close();
//			st.close();
//			con.close();
			
		}
		catch(Exception ex) {System.out.println("Error List : "+ex);}
		return lst;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getProdid() {
		return prodid;
	}

	public void setProdid(int prodid) {
		this.prodid = prodid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
