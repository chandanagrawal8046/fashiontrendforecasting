package com.fashion.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class PredictionFuns 
{
	Connection con;
    Statement st;
    ResultSet rs;
    
    public int FetchMax(String tblnm,String fldnm)
    {
       int maxid=0;
        try
        {
            con=DBConnector.connectDB();
            String qr="select max("+fldnm+") as mxid from "+tblnm;
            System.out.println(qr);
            st=con.createStatement();
            rs=st.executeQuery(qr);
            while(rs.next())
            maxid=rs.getInt(1);    
            con.close();
        }
        catch(Exception ex)
        {  
            maxid=1001;
            System.out.println(ex);
        }    
        finally
        {
        	
        }
        
        System.out.println("Max id : "+maxid+1);
        return (maxid+1);
    } 
    
    
    public Vector<String> getValue(String qr,int nocol)
    {    
        Connection con=null;
        Statement st;
        ResultSet rs;
    
        Vector<String> v=new Vector<String>();       
        v.clear();
         try{      
             con=DBConnector.connectDB();
           st=con.createStatement();
           System.out.println("query="+qr);
           rs=st.executeQuery(qr);
          
           while(rs.next())
           {
               for(int i=1;i<=nocol;i++)
               {
               v.addElement(rs.getString(i));
               System.out.println(rs.getString(i));
               }              
           }
         }
         catch(Exception e)
        {
            System.out.println("Error in processing due to "+e);
        }   
        return(v);        
    }


}
