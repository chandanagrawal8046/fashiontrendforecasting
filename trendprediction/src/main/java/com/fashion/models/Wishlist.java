package com.fashion.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.fashion.services.DBConnector;

public class Wishlist 
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
	
	public Wishlist()
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
	}
	
	public boolean createWishlist()
	{
		boolean flag=false;
		Connection con;
		CallableStatement cst;
        int n=0;
		
		try
		{
			con=DBConnector.connectDB();
			System.out.println("Name : "+prodid);
			
			cst=con.prepareCall("{call addtowishlist(?,?)}");
            
			cst.setString(1,userid);
			cst.setInt(2,prodid);
                       
            n=cst.executeUpdate();
            
            if(n>0)
            {               
                flag=true;                     
            }
			
		}
		catch(Exception ex)
		{
			System.out.println("wishlist error : "+ex);
		}
		
		return flag;
	}
	
	
	public boolean removeFromWishlist()
	{
		boolean flag=false;
		Connection con;
		PreparedStatement pst;
        int n=0;
		
		try
		{
			con=DBConnector.connectDB();
			System.out.println("Name : "+id);
			
			pst=con.prepareStatement("delete from wishlist where id=? and userid=?");       
			pst.setInt(1,id);
			pst.setString(2, userid);
                       
            n=pst.executeUpdate();
            
            if(n>0)
            {               
                flag=true;                     
            }
			
		}
		catch(Exception ex)
		{
			System.out.println("wishlist error : "+ex);
		}
		
		return flag;
	}
	
	
	public ArrayList<Wishlist> getMyWishlist()
	{
		ArrayList<Wishlist> obj=new ArrayList<Wishlist>();
		
		try
		{			
			Connection con=DBConnector.connectDB();
			Statement st=con.createStatement();
						
			ResultSet rs=st.executeQuery("select * from mywishlist where userid='"+userid+"' and status='active' order by id desc");
			
			Wishlist p;
			while(rs.next())
			{				
				p=new Wishlist();
				p.id=rs.getInt("id");
				p.prodid=rs.getInt("prodid");
				p.title=rs.getString("title");
				p.description=rs.getString("pdescription");
				p.imgurl=rs.getString("imgurl");
				p.brand=rs.getString("brand");				
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
	
	

}
