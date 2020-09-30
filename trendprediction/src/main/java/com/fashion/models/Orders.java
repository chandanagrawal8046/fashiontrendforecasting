package com.fashion.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import com.fashion.services.DBConnector;
import com.fashion.services.PredictionFuns;

public class Orders 
{
	
	private int orderno;
	private String userid;
	private String orderdt;
	private double bill;
	private String paymode;
	private String paystatus;
	private String orderstatus;
	private String name;
	private String clause;
	private String act;
	
	
	public Orders()
	{
		orderno=0;
		userid="";
		orderdt="";
		bill=0;
		paymode="";
		paystatus="";
		orderstatus="";
		name="";
		clause="";
	}
	
	public boolean submit()
    {
        boolean flag=false;        
        CallableStatement cst;
        int n=0;
        
        try
        {
            Connection con=DBConnector.connectDB();                                                
            System.out.println("Name : "+userid);
                        
            cst=con.prepareCall("{call placeorder(?,?,?,?)}");
            
            cst.setString(1,userid);
            cst.setString(2,orderdt);            
            cst.setDouble(3,bill);            
            cst.setInt(4,orderno);
            
            n=cst.executeUpdate();            
            if(n>0)
            {               
                flag=true;      
                
                PredictionFuns f=new PredictionFuns();
                Vector<String> vct=new Vector<String>();
                vct=f.getValue("select prodid,quantity from shoppingcart where orderno="+orderno, 2);
                if(vct.size()>0)
                {
                    for(int i=0;i<vct.size();i+=2)
                    {
                        int pid=Integer.parseInt(vct.elementAt(i).toString());
                        int quan=Integer.parseInt(vct.elementAt(i+1).toString());                        
                        updateStock(pid, quan);                            
                    }
                }
                
                
            }
        }
        catch(Exception ex)
        { 
            flag=false;
            System.out.println(ex);
        }
        return flag;
    }
    
    public boolean updateStock(int prodid,int quantity)
    {
        boolean flag=false;        
        PreparedStatement pst;
        int n=0;
        
        try
        {
        	Connection con=DBConnector.connectDB();                                                
            String qr="update products set stock=stock-"+quantity+" where id="+prodid;
            System.out.println(qr);
            pst=con.prepareStatement(qr);
            
            n=pst.executeUpdate();            
            if(n>0)
            {               
                flag=true;                     
            }
        }
        catch(Exception ex)
        { 
            flag=false;
            System.out.println(ex);
        }
        return flag;
    }
    
    
    public ArrayList<Orders> getOrdersReport()
	{
		ArrayList<Orders> obj=new ArrayList<Orders>();
		
		try
		{			
			Connection con=DBConnector.connectDB();
			Statement st=con.createStatement();
			
			String qr="";
			
			qr="select distinct(orderno) 'orderno',userid,name,orderdt,bill,paymode,paystatus,orderstatus from orderdetails "+clause+" order by orderno desc";
						
			ResultSet rs=st.executeQuery(qr);
			
			Orders c;
			while(rs.next())
			{				
				c=new Orders();
				c.setOrderno(rs.getInt("orderno"));
				c.setUserid(rs.getString("userid"));
				c.setName(rs.getString("name"));				
				c.setOrderdt(rs.getString("orderdt"));
				c.setBill(rs.getDouble("bill"));
				c.setPaymode(rs.getString("paymode"));
				c.setPaystatus(rs.getString("paystatus"));
				c.setOrderstatus(rs.getString("orderstatus"));
				
				obj.add(c);
				System.out.println(rs.getInt("orderno")+"  "+rs.getString("name")+"		"+rs.getString("orderdt"));
			}			
		}
		catch(Exception ex) {System.out.println("Error List : "+ex);}
		return obj;
	}
    
    
    public boolean updateOrderStatus()
    {
        boolean flag=false;
        int n=0;
        
        try
        {
        	Connection con=DBConnector.connectDB();                                                            
            CallableStatement cst=con.prepareCall("{call processorder(?,?)}");
            
            cst.setInt(1,orderno);
            cst.setString(2,act);  
            
            n=cst.executeUpdate();            
            if(n>0)
            {               
                flag=true;                     
            }
            
            cst.close();
            con.close();
        }
        catch(Exception ex)
        { 
            flag=false;
            System.out.println(ex);
        }
        return flag;
    }
    
    
    
    public boolean cancelOrder()
    {
        boolean flag=false;
        int n=0;
        
        try
        {
        	Connection con=DBConnector.connectDB();      
        	Statement st=con.createStatement();
			
			String qr="select prodid,quantity from shoppingcart where orderno="+orderno;
			ResultSet rs=st.executeQuery(qr);
			
			int proid=0,quant=0;
			while(rs.next())
			{								
				proid=rs.getInt("prodid");
				quant=rs.getInt("quantity");
				System.out.println(proid+"  "+quant);
				
				cancelProduct(proid, quant);
			}
        	
            PreparedStatement pst=con.prepareStatement("update orders set orderstatus='cancelled' where orderno=?");
            
            pst.setInt(1,orderno);
            
            n=pst.executeUpdate();            
            if(n>0)
            {               
                flag=true;                     
            }
            
            pst.close();
            con.close();
        }
        catch(Exception ex)
        { 
            flag=false;
            System.out.println(ex);
        }
        return flag;
    }
    
    private boolean cancelProduct(int prodid,int quantity)
    {
    	boolean flag=false;
        int n=0;
        
        try
        {
        	System.out.println("update products set stock=stock+"+quantity+" where id="+prodid);
        	
        	Connection con=DBConnector.connectDB();      
        	PreparedStatement cst=con.prepareStatement("update products set stock=stock+? where id=?");
        	
            cst.setInt(1,quantity);
            cst.setInt(2,prodid);
            
            n=cst.executeUpdate();            
            if(n>0)
            {               
                flag=true;                     
            }
            
            cst.close();
            con.close();
        }
        catch(Exception ex)
        { 
            flag=false;
            System.out.println(ex);
        }
        return flag;
    }
    
    
    public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public String getClause() {
		return clause;
	}

	public void setClause(String clause) {
		this.clause = clause;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrderno() {
		return orderno;
	}

	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOrderdt() {
		return orderdt;
	}

	public void setOrderdt(String orderdt) {
		this.orderdt = orderdt;
	}

	public double getBill() {
		return bill;
	}

	public void setBill(double bill) {
		this.bill = bill;
	}

	public String getPaymode() {
		return paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}

	public String getPaystatus() {
		return paystatus;
	}

	public void setPaystatus(String paystatus) {
		this.paystatus = paystatus;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
    
    

}
