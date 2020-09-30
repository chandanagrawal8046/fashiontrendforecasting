package com.fashion.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.fashion.services.DBConnector;
import com.fashion.services.PredictionFuns;

public class CartManager 
{
	Connection con;
    PreparedStatement pst;
    CallableStatement cst;
    ResultSet rs;
    String qr="";
    PredictionFuns f=new PredictionFuns();
    
    public int startCart()
    {
        int orderno=0;
        try
        {
            orderno=f.FetchMax("orders","orderno");
            if(orderno==1 || orderno==1001)
                orderno=86321;
            
            con=DBConnector.connectDB();
//            cst=con.prepareCall("{call startorder(?)}");
            
            String qr="insert into orders(orderno,orderstatus) values("+orderno+",'trial');";
            System.out.println("Query : "+qr);
            
            pst=con.prepareStatement(qr);
            
//            pst.setInt(1, orderno);
            
            if(pst.executeUpdate()>0)
            System.out.println("Order started");
        }
        catch(Exception ex){System.out.println("Order:"+ex);}
        
        return orderno;
    }
    
    
    public boolean deleteTrials()
    {
        boolean flag=false;
        try
        {               
            Vector<String> vct=new Vector<String>();
            vct=f.getValue("select orderno from orders where orderstatus='trial'", 1);
            con=DBConnector.connectDB();
            if(!vct.isEmpty())
            {
                for(int i=0;i<vct.size();i++)
                {
                    int ono=Integer.parseInt(vct.elementAt(i).toString());
                    
                    pst=con.prepareStatement("delete from shoppingcart where orderno="+ono);
                    pst.executeUpdate();
                    
                    pst=con.prepareStatement("delete from orders where orderno="+ono);
                    pst.executeUpdate();     
                    
                    flag=true;
                }
            }
            
        }
        catch(Exception ex)
        {
            System.out.println("Order:"+ex);
            flag=false;
        }        
        return flag;
    }
    
    public boolean addToCart(int orderno,int prodid,int quantity)
    {       
        boolean flag=false;
        try
        {           
            con=DBConnector.connectDB();
            cst=con.prepareCall("{call addtocart(?,?,?)}");
            
            cst.setInt(1,orderno);
            cst.setInt(2,prodid);
            cst.setInt(3,quantity);
            
            if(cst.executeUpdate()>0)
            {
                flag=true;
                System.out.println("added to cart");
            }
            else
            {
                flag=false;
                System.out.println("not added to cart");
            }
        }
        catch(Exception ex)
        {
            System.out.println("Order:"+ex);
            flag=false;
        }        
        return flag;
    }
    
    public boolean delFromCart(int cartid)
    {       
        boolean flag=false;
        try
        {           
            con=DBConnector.connectDB();
            cst=con.prepareCall("{call DeleteFromCart(?)}");
            
            cst.setInt(1,cartid);
            
            if(cst.executeUpdate()>0)
            {
                flag=true;
                System.out.println("removed from cart");
            }
            else
            {
                flag=false;
                System.out.println("not removed from cart");
            }
        }
        catch(Exception ex)
        {
            System.out.println("Order:"+ex);
            flag=false;
        }        
        return flag;
    }

}
