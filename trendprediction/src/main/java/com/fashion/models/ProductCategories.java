package com.fashion.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.fashion.services.DBConnector;

public class ProductCategories 
{
	private int catid;
	private String catname;	
	private String catdate;
	private String status;
	
	public ProductCategories()
	{
		catid=0;
		catname="";		
		catdate="";
		status="";
	}
	
	public boolean addNewCategory()
	{
		boolean flag=false;
		Connection con;
		CallableStatement cst;
        int n=0;
		
		try
		{
			con=DBConnector.connectDB();
			System.out.println("Name : "+catname);
			
			cst=con.prepareCall("{call newcategory(?)}");
            
            cst.setString(1,catname);
                       
            n=cst.executeUpdate();
            
            if(n>0)
            {               
                flag=true;                     
            }
			
		}
		catch(Exception ex)
		{
			System.out.println("Category error : "+ex);
		}
		
		return flag;	
	}
	
	public boolean deleteCategory()
	{
		boolean flag=false;
		Connection con;
		CallableStatement cst;
        int n=0;
		
		try
		{
			con=DBConnector.connectDB();
			System.out.println("Name : "+catname);
			
			cst=con.prepareCall("{call deletecategory(?)}");
            
            cst.setInt(1,catid);
                                    
            n=cst.executeUpdate();
            
            if(n>0)
            {               
                flag=true;                     
            }
			
		}
		catch(Exception ex)
		{
			System.out.println("Cat error : "+ex);
		}
		
		return flag;	
	}
	
	
	public ArrayList<ProductCategories> getReport()
	{
		ArrayList<ProductCategories> obj=new ArrayList<ProductCategories>();
		
		try
		{			
			Connection con=DBConnector.connectDB();
			Statement st=con.createStatement();
						
			ResultSet rs=st.executeQuery("select  id,catname,DATE_FORMAT(catdt, '%d/%m/%Y %h:%m:%s') 'catdt',status from prodcategories where status='active' order by catname");
			
			ProductCategories c;
			while(rs.next())
			{				
				c=new ProductCategories();
				c.catid=rs.getInt("id");
				c.catname=rs.getString("catname");
				
				c.catdate=rs.getString("catdt");
				c.status=rs.getString("status");
				obj.add(c);
				System.out.println(rs.getInt("id")+"  "+rs.getString("catname")+"		"+rs.getString("catdt"));
			}			
		}
		catch(Exception ex) {System.out.println("Error List : "+ex);}
		return obj;
	}
	
	
	public ArrayList<ProductCategories> getCompanyProdCategories(String company)
	{
		ArrayList<ProductCategories> obj=new ArrayList<ProductCategories>();
		
		try
		{			
			Connection con=DBConnector.connectDB();
			Statement st=con.createStatement();
			
			String qr="select distinct(p.categories) 'catname', c.id,catdt,c.status from products p,prodcategories c where p.categories=c.catname and c.status='active' and p.brand='"+company+"'";
			System.out.println(qr);
			
			ResultSet rs=st.executeQuery(qr);
			
			ProductCategories c;
			while(rs.next())
			{				
				c=new ProductCategories();
				c.catid=rs.getInt("id");
				c.catname=rs.getString("catname");
				
				c.catdate=rs.getString("catdt");
				c.status=rs.getString("status");
				obj.add(c);
				System.out.println(rs.getInt("id")+"  "+rs.getString("catname")+"		"+rs.getString("catdt"));
			}			
		}
		catch(Exception ex) {System.out.println("Error List : "+ex);}
		return obj;
	}
	
	public int getCatid() {
		return catid;
	}

	public void setCatid(int catid) {
		this.catid = catid;
	}

	public String getCatname() {
		return catname;
	}

	public void setCatname(String catname) {
		this.catname = catname;
	}

	public String getCatdate() {
		return catdate;
	}

	public void setCatdate(String catdate) {
		this.catdate = catdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
