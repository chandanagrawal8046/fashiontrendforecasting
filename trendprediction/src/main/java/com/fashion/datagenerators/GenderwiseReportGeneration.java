package com.fashion.datagenerators;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.fashion.services.DBConnector;

public class GenderwiseReportGeneration 
{
	private int hits1;
	private int hits2;
	
	private String gender1;
	private String gender2;
	
	private int hits;
	private String gender;
	
	public GenderwiseReportGeneration()
	{
		gender1="Male";
		hits1=0;
		gender2="Female";
		hits2=0;
		
		gender="";
		hits=0;
	}
	
	public ArrayList<GenderwiseReportGeneration> getGenderwiseReport()
	{
		ArrayList<GenderwiseReportGeneration> lst=new ArrayList<GenderwiseReportGeneration>();
		
		try
		{
			String qr1="select count(*) as hits from trendsview where gender='male'";			
			String qr2="select count(*) as hits from trendsview where gender='female'";
						
			Connection con=DBConnector.connectDB();
			Statement st=con.createStatement();
						
			ResultSet rs=st.executeQuery(qr1);
			
			GenderwiseReportGeneration obj;
			
			System.out.println(qr1);
			while(rs.next())
			{
				hits1=rs.getInt("hits");
				System.out.println(rs.getInt("hits"));
				
				obj=new GenderwiseReportGeneration();
				obj.setGender(gender1);
				obj.setHits(hits1);
				
				lst.add(obj);
			}
			
			System.out.println(qr2);
			rs=st.executeQuery(qr2);
			while(rs.next())
			{
				hits2=rs.getInt("hits");
				System.out.println(rs.getInt("hits"));
				
				obj=new GenderwiseReportGeneration();
				obj.setGender(gender2);
				obj.setHits(hits2);
				
				lst.add(obj);
			}
			
			st.close();
			con.close();
		}
		catch(Exception ex) {}
		
		return lst;
	}

	public int getHits1() {
		return hits1;
	}

	public void setHits1(int hits1) {
		this.hits1 = hits1;
	}

	public int getHits2() {
		return hits2;
	}

	public void setHits2(int hits2) {
		this.hits2 = hits2;
	}

	public String getGender1() {
		return gender1;
	}

	public void setGender1(String gender1) {
		this.gender1 = gender1;
	}

	public String getGender2() {
		return gender2;
	}

	public void setGender2(String gender2) {
		this.gender2 = gender2;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	

}
