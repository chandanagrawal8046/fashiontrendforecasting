package com.fashion.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import com.fashion.services.DBConnector;
import com.fashion.services.PredictionFuns;

public class Preferences 
{
	
	public void trackPreferences(String userid)
	{
		try
		{
			Connection con=DBConnector.connectDB();
			PreparedStatement pst=con.prepareStatement("delete from preferences where userid='"+userid+"'");
			pst.executeUpdate();
			pst.close();
			con.close();
			
			Vector<String> vct=new Vector<String>();
			PredictionFuns pf=new PredictionFuns();
			String qr="select count(categories) 'hits',categories,userid from prefview where userid='"+userid+"' group by categories order by hits desc limit 3";
			vct=pf.getValue(qr, 3);
			
			String pref="NA";
			
			if(vct.size()>0)
			{
				for(int i=0;i<vct.size();i+=3)
				{
					int hits=Integer.parseInt(String.valueOf(vct.elementAt(i)));
					pref=String.valueOf(vct.elementAt(i+1));
					
					addToPref(userid, pref, hits);
				}				
			}
			
		}
		catch(Exception ex)
		{
			System.out.println("Pref Exc : "+ex);
		}
	}
	
	private boolean addToPref(String userid,String pref,int hits)
	{
		boolean flag=false;
		Connection con;
		PreparedStatement pst;
	    int n=0;
		
		try
		{
			con=DBConnector.connectDB();
			System.out.println(userid+" : "+pref+" : "+hits);
			
			
			
			pst=con.prepareStatement("insert into preferences values(default,?,?,?)");
	        
			pst.setString(1,userid);
			pst.setString(2,pref);
			pst.setInt(3,hits);
				                   
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
	
	

}
