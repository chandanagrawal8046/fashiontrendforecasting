package com.fashion.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import com.fashion.services.DBConnector;
import com.fashion.services.PredictionFuns;

public class Recommendations 
{
	
	private int id;
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
	private String pref;
	private int hits;
	
	
	public ArrayList<Recommendations> lst1;
	public ArrayList<Recommendations> lst2;
	public ArrayList<Recommendations> lst3;
	
	public Recommendations()
	{
		id=0;
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
		pref="";
		hits=0;		
		
		lst1=new ArrayList<Recommendations>();
		lst2=new ArrayList<Recommendations>();
		lst3=new ArrayList<Recommendations>();
	}
	
	
	public void getReport()
	{
		String pref1="";
		int hits1=0;
		String pref2="";
		int hits2=0;
		String pref3="";
		int hits3=0;
		
		try
		{
			Vector<String> vct=new Vector<String>();
			PredictionFuns pf=new PredictionFuns();
			
			vct=pf.getValue("select * from preferences where userid='"+userid+"'", 4);
			System.out.println("Vector Size : "+vct.size());
			if(vct.size()>0)
			{
				pref1=String.valueOf(vct.elementAt(2));
				hits1=Integer.parseInt(String.valueOf(vct.elementAt(3)));
				
				if(vct.size()>4)
				{
					pref2=String.valueOf(vct.elementAt(6));
					hits2=Integer.parseInt(String.valueOf(vct.elementAt(7)));
					
					if(vct.size()>8)
					{
						pref3=String.valueOf(vct.elementAt(10));
						hits3=Integer.parseInt(String.valueOf(vct.elementAt(11)));
					}
				}				
			}
			
			System.out.println(pref1+" : "+hits1);
			System.out.println(pref2+" : "+hits2);
			System.out.println(pref3+" : "+hits3);
			
			String qr="";
			Connection con=DBConnector.connectDB();
			Statement st=con.createStatement();
			qr="select * from products where categories='"+pref1+"' and status='active'";
			System.out.println("Query : "+qr);
			ResultSet rs=st.executeQuery(qr);						
			Recommendations p;
			while(rs.next())
			{				
				p=new Recommendations();
				
				p.prodid=rs.getInt("id");
				p.title=rs.getString("title");
				p.description=rs.getString("pdescription");
				p.imgurl=rs.getString("imgurl");
				p.brand=rs.getString("brand");				
				p.stock=rs.getInt("stock");
				p.status=rs.getString("status");
				p.price=rs.getDouble("price");
				p.categories=rs.getString("categories");
				p.pref=pref1;
				p.hits=hits1;
				
				lst1.add(p);
				System.out.println(rs.getInt("id")+"  "+rs.getString("title")+"		"+rs.getString("categories"));
			}	
			rs.close();
			
			qr="select * from products where categories='"+pref2+"' and status='active'";
			System.out.println("Query : "+qr);
			rs=st.executeQuery(qr);
			while(rs.next())
			{				
				p=new Recommendations();
				
				p.prodid=rs.getInt("id");
				p.title=rs.getString("title");
				p.description=rs.getString("pdescription");
				p.imgurl=rs.getString("imgurl");
				p.brand=rs.getString("brand");				
				p.stock=rs.getInt("stock");
				p.status=rs.getString("status");
				p.price=rs.getDouble("price");
				p.categories=rs.getString("categories");
				p.pref=pref2;
				p.hits=hits2;
				
				lst2.add(p);
				System.out.println(rs.getInt("id")+"  "+rs.getString("title")+"		"+rs.getString("categories"));
			}
			rs.close();
			
			qr="select * from products where categories='"+pref3+"' and status='active'";
			System.out.println("Query : "+qr);
			rs=st.executeQuery(qr);
			while(rs.next())
			{				
				p=new Recommendations();
				
				p.prodid=rs.getInt("id");
				p.title=rs.getString("title");
				p.description=rs.getString("pdescription");
				p.imgurl=rs.getString("imgurl");
				p.brand=rs.getString("brand");				
				p.stock=rs.getInt("stock");
				p.status=rs.getString("status");
				p.price=rs.getDouble("price");
				p.categories=rs.getString("categories");
				p.pref=pref3;
				p.hits=hits3;
				
				lst3.add(p);
				System.out.println(rs.getInt("id")+"  "+rs.getString("title")+"		"+rs.getString("categories"));
			}	
		}
		catch(Exception ex) {System.out.println("Error List : "+ex);}
		//return obj;
	}


	public ArrayList<Recommendations> getLst1() {
		return lst1;
	}


	public void setLst1(ArrayList<Recommendations> lst1) {
		this.lst1 = lst1;
	}


	public ArrayList<Recommendations> getLst2() {
		return lst2;
	}


	public void setLst2(ArrayList<Recommendations> lst2) {
		this.lst2 = lst2;
	}


	public ArrayList<Recommendations> getLst3() {
		return lst3;
	}


	public void setLst3(ArrayList<Recommendations> lst3) {
		this.lst3 = lst3;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public String getPref() {
		return pref;
	}


	public void setPref(String pref) {
		this.pref = pref;
	}


	public int getHits() {
		return hits;
	}


	public void setHits(int hits) {
		this.hits = hits;
	}



	
	
}
