package com.fashion.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import com.fashion.datagenerators.CustomersDetails;
import com.fashion.services.DBConnector;
import com.fashion.services.PredictionFuns;

public class ProfilewiseRecommendation 
{
	private String userid;
	private String title;
	private double price;
	private String imgurl;
	private int prodid;
	private String description;
	private String brand;
		
	public ArrayList<ProfilewiseRecommendation> getAgewiseRecommendation()
	{
		ArrayList<ProfilewiseRecommendation> lst=new ArrayList<ProfilewiseRecommendation>();
		
		try
		{			
			CustomersDetails dtl=new CustomersDetails(userid);
			
			int age=dtl.getAge();
			String clause="";
			if(age>=10 && age<30)
				clause="between 10 and 30";
			else if(age>=30 && age<50)
				clause="between 30 and 50";
			else if(age>=50 && age<70)
				clause="between 50 and 70";
			else if(age>=70 && age<90)
				clause="between 70 and 90";
			else if(age>=90 && age<100)
				clause="between 90 and 100";
			
			PredictionFuns pf=new PredictionFuns();
			Vector<String> vct=new Vector<String>();
			
			vct=pf.getValue("select userid from customers where userid<>'"+userid+"' and age "+clause, 1);
			
			String useridclause="(";
			
			if(vct.size()>0)
			{
				useridclause+="'"+vct.elementAt(0)+"'";
				
				if(vct.size()>1)
				{
					for(int i=0;i<vct.size();i++)
					{
						useridclause+=",'"+vct.elementAt(i)+"'";
					}
				}
			}
			
			useridclause+=")";
			
			Connection con=DBConnector.connectDB();
			Statement st=con.createStatement();
			String qr="select distinct(prodid) 'prodid',o.title,o.imgurl,o.price,p.brand,p.pdescription from orderdetails o, products p where p.id=o.prodid and userid in "+useridclause;
			System.out.println("Query : "+qr);
			ResultSet rs=st.executeQuery(qr);						
			
			ProfilewiseRecommendation p;
			while(rs.next())
			{				
				p=new ProfilewiseRecommendation();
				
				p.setProdid(rs.getInt("prodid"));
				p.title=rs.getString("title");
				p.imgurl=rs.getString("imgurl");
				p.price=rs.getDouble("price");
				p.description=rs.getString("pdescription");
				p.brand=rs.getString("brand");
				
				lst.add(p);
				System.out.println(rs.getInt("prodid")+"  "+rs.getString("title"));
			}	
			rs.close();			
		}
		catch(Exception ex) {}
		
		return lst;
	}
	
	
	public ArrayList<ProfilewiseRecommendation> getGenderwiseRecommendation()
	{
		ArrayList<ProfilewiseRecommendation> lst=new ArrayList<ProfilewiseRecommendation>();
		
		try
		{			
			CustomersDetails dtl=new CustomersDetails(userid);
			
			String gender=dtl.getGender();
			
			PredictionFuns pf=new PredictionFuns();
			Vector<String> vct=new Vector<String>();
			
			vct=pf.getValue("select userid from customers where userid<>'"+userid+"' and gender='"+gender+"' ", 1);
			
			String useridclause="(";
			
			if(vct.size()>0)
			{
				useridclause+="'"+vct.elementAt(0)+"'";
				
				if(vct.size()>1)
				{
					for(int i=0;i<vct.size();i++)
					{
						useridclause+=",'"+vct.elementAt(i)+"'";
					}
				}
			}
			
			useridclause+=")";
			
			Connection con=DBConnector.connectDB();
			Statement st=con.createStatement();
			String qr="select distinct(prodid) 'prodid',o.title,o.imgurl,o.price,p.brand,p.pdescription from orderdetails o, products p where p.id=o.prodid and userid in "+useridclause;
			System.out.println("Query : "+qr);
			ResultSet rs=st.executeQuery(qr);						
			
			ProfilewiseRecommendation p;
			while(rs.next())
			{				
				p=new ProfilewiseRecommendation();
				
				p.setProdid(rs.getInt("prodid"));
				p.title=rs.getString("title");
				p.imgurl=rs.getString("imgurl");
				p.price=rs.getDouble("price");
				p.description=rs.getString("pdescription");
				p.brand=rs.getString("brand");
				
				lst.add(p);
				System.out.println(rs.getInt("prodid")+"  "+rs.getString("title"));
			}	
			rs.close();			
		}
		catch(Exception ex) {}
		
		return lst;
	}

	public int getProdid() {
		return prodid;
	}

	public void setProdid(int prodid) {
		this.prodid = prodid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	

}
