package com.fashion.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.fashion.services.DBConnector;


public class BrowsingHistory 
{
	private int id,	orderno,prodid,stock;
	private String categories,userid,sessionid,	hostip;
	private double price;
	private String title,imgurl,brand;
	
	
	public BrowsingHistory()
	{
		id=0;
		orderno=0;
		prodid=0;
		stock=0;
		price=0;
		categories="";
		userid="";
		sessionid="";
		hostip="";		
		title="";
		imgurl="";
		brand="";
	}
	
	public boolean track() 
	{
		boolean flag=false;
        try
        {     
        	Connection con;            
            CallableStatement cst;
            
            con=DBConnector.connectDB();
            cst=con.prepareCall("{call trackBrowsing(?,?,?,?)}");
            
            cst.setInt(1,prodid);
            cst.setInt(2,orderno);
            cst.setString(3,sessionid);
            cst.setString(4,hostip);
            
            if(cst.executeUpdate()>0)
            {
                flag=true;
                System.out.println("tracked");
            }
            else
            {
                flag=false;
                System.out.println("not tracked");
            }
        }
        catch(Exception ex)
        {
            System.out.println("tracked error:"+ex);
            flag=false;
        }        
        return flag;
	}
	
	
	public ArrayList<BrowsingHistory> getHistory()
	{
		ArrayList<BrowsingHistory> lst=new ArrayList<BrowsingHistory>();
	
		try
		{
			String qr="select * from historydetails where hostip like '%"+hostip+"%'";
	        System.out.println(qr);
	        Connection con=DBConnector.connectDB();        
	        Statement st=con.createStatement();
	        ResultSet rs=st.executeQuery(qr);
	        
	        BrowsingHistory obj;
	        while(rs.next())
	        {	
	            obj=new BrowsingHistory();
	            System.out.println(rs.getString("title"));	            
	            obj.setProdid(rs.getInt("prodid"));
	            obj.setTitle(rs.getString("title"));
	            obj.setCategories(rs.getString("categories"));
	            obj.setPrice(rs.getDouble("price"));
	            obj.setImgurl(rs.getString("imgurl"));
	            obj.setBrand(rs.getString("brand"));
	            obj.setStock(rs.getInt("stock"));
	            obj.setHostip(rs.getString("hostip"));
	            
	            lst.add(obj);  
	        }
        }
		catch(Exception ex) {}
		return lst;
		
	}
	
	public ArrayList<BrowsingHistory> getRemovedProdHistory()
	{
		ArrayList<BrowsingHistory> lst=new ArrayList<BrowsingHistory>();
	
		try
		{
			String qr="select * from removedDetails where userid='"+userid+"'";
	        System.out.println(qr);
	        Connection con=DBConnector.connectDB();        
	        Statement st=con.createStatement();
	        ResultSet rs=st.executeQuery(qr);
	        
	        BrowsingHistory obj;
	        while(rs.next())
	        {	
	            obj=new BrowsingHistory();
	            System.out.println(rs.getString("title"));	            
	            obj.setProdid(rs.getInt("prodid"));
	            obj.setTitle(rs.getString("title"));
	            obj.setCategories(rs.getString("categories"));
	            obj.setPrice(rs.getDouble("price"));
	            obj.setImgurl(rs.getString("imgurl"));
	            obj.setBrand(rs.getString("brand"));
	            obj.setStock(rs.getInt("stock"));
	            	            
	            lst.add(obj);  
	        }
        }
		catch(Exception ex) {}
		return lst;
		
	}
	
	

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getHostip() {
		return hostip;
	}

	public void setHostip(String hostip) {
		this.hostip = hostip;
	}
	
	

}
