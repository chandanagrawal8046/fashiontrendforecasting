package com.fashion.datagenerators;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.fashion.services.DBConnector;

public class TopSoldProducts 
{
	private int hits;
	private String title;
	private int prodid;
	private String brand;
	private String categories;
	
	private int hit[];
	private String name[];
	
	public TopSoldProducts()
	{
		hit=new int[10];
		name=new String[10];
		
		hits=0;
		title="";
		prodid=0;
		brand="";
		categories="";
	}
	
	public ArrayList<TopSoldProducts> getReport()
	{
		ArrayList<TopSoldProducts> lst=new ArrayList<TopSoldProducts>();
		try
		{
			String qr="select count(s.prodid) 'hits',s.title,s.prodid,p.brand,p.categories from shoppingcart s,products p ";
			qr+=" where s.prodid=p.id group by s.prodid order by hits desc limit 10;";			
			System.out.println(qr);
			
			Connection con=DBConnector.connectDB();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(qr);
			
			TopSoldProducts obj;
			int i=0;
			while(rs.next())
			{
				hits=rs.getInt("hits");				
				title=rs.getString("title");
				System.out.println(title+" : "+hits);
				
				hit[i]=hits;
				name[i]=title;
				
				obj=new TopSoldProducts();
				obj.setHits(hits);
				obj.setTitle(title);
				obj.setBrand(rs.getString("brand"));
				obj.setCategories(rs.getString("categories"));
				
				lst.add(obj);
				i++;
			}
			
		}
		catch(Exception ex) {}
		return lst;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getProdid() {
		return prodid;
	}

	public void setProdid(int prodid) {
		this.prodid = prodid;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public int[] getHit() {
		return hit;
	}

	public void setHit(int[] hit) {
		this.hit = hit;
	}

	public String[] getName() {
		return name;
	}

	public void setName(String[] name) {
		this.name = name;
	}
	
	

}
