package com.fashion.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.fashion.services.DBConnector;

public class Products 
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
		
	public Products()
	{
		id=0;
		title="";
		categories="";
		description="";
		price=0;
		imgurl="";
		brand="";
		alsoviewed="NA";
		asinno="";
		stock=0;
		status="";
	}
	
	public boolean addProduct()
	{
		boolean flag=false;
		Connection con;
		CallableStatement cst;
        int n=0;
		
		try
		{
			con=DBConnector.connectDB();
			System.out.println("Name : "+title);
			
			cst=con.prepareCall("{call newproduct(?,?,?,?,?,?,?,?,?,?)}");
            
			cst.setInt(1,id);
            cst.setString(2,title);
            cst.setString(3,categories);
            cst.setString(4,description);
            cst.setDouble(5,price);
            cst.setString(6,imgurl);
            cst.setString(7,brand);
            cst.setString(8,alsoviewed);
            cst.setString(9,asinno);
            cst.setInt(10,stock);
                       
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
	
	public ArrayList<Products> getCatProducts()
	{
		ArrayList<Products> obj=new ArrayList<Products>();
		
		try
		{			
			Connection con=DBConnector.connectDB();
			Statement st=con.createStatement();
						
			ResultSet rs=st.executeQuery("select * from products where categories='"+categories+"' and status='active'");
			
			Products p;
			while(rs.next())
			{				
				p=new Products();
				p.id=rs.getInt("id");
				p.title=rs.getString("title");
				p.description=rs.getString("pdescription");
				p.asinno=rs.getString("asinNo");				
				p.imgurl=rs.getString("imgurl");
				p.brand=rs.getString("brand");				
				p.alsoviewed=rs.getString("alsoviewed");
				p.stock=rs.getInt("stock");
				p.status=rs.getString("status");
				p.price=rs.getDouble("price");
				p.categories=rs.getString("categories");
				obj.add(p);
				System.out.println(rs.getInt("id")+"  "+rs.getString("title")+"		"+rs.getString("categories"));
			}			
		}
		catch(Exception ex) {System.out.println("Error List : "+ex);}
		return obj;
	}
	
	public ArrayList<Products> getCompanyCatProducts()
	{
		ArrayList<Products> obj=new ArrayList<Products>();
		
		try
		{			
			Connection con=DBConnector.connectDB();
			Statement st=con.createStatement();
						
			ResultSet rs=st.executeQuery("select * from products where categories='"+categories+"' and status='active' and brand='"+brand+"'");
			
			Products p;
			while(rs.next())
			{				
				p=new Products();
				p.id=rs.getInt("id");
				p.title=rs.getString("title");
				p.description=rs.getString("pdescription");
				p.asinno=rs.getString("asinNo");				
				p.imgurl=rs.getString("imgurl");
				p.brand=rs.getString("brand");				
				p.alsoviewed=rs.getString("alsoviewed");
				p.stock=rs.getInt("stock");
				p.status=rs.getString("status");
				p.price=rs.getDouble("price");
				p.categories=rs.getString("categories");
				obj.add(p);
				System.out.println(rs.getInt("id")+"  "+rs.getString("title")+"		"+rs.getString("categories"));
			}			
		}
		catch(Exception ex) {System.out.println("Error List : "+ex);}
		return obj;
	}
	
	public ArrayList<Products> getAllProducts()
	{
		ArrayList<Products> obj=new ArrayList<Products>();
		
		try
		{			
			Connection con=DBConnector.connectDB();
			Statement st=con.createStatement();
						
			ResultSet rs=st.executeQuery("select * from products where status='active'");
			
			Products p;
			while(rs.next())
			{				
				p=new Products();
				p.id=rs.getInt("id");
				p.title=rs.getString("title");
				p.description=rs.getString("pdescription");
				p.asinno=rs.getString("asinNo");				
				p.imgurl=rs.getString("imgurl");
				p.brand=rs.getString("brand");				
				p.alsoviewed=rs.getString("alsoviewed");
				p.stock=rs.getInt("stock");
				p.status=rs.getString("status");
				p.price=rs.getDouble("price");
				p.categories=rs.getString("categories");
				obj.add(p);
				System.out.println(rs.getInt("id")+"  "+rs.getString("title")+"		"+rs.getString("categories"));
			}			
		}
		catch(Exception ex) {System.out.println("Error List : "+ex);}
		return obj;
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
