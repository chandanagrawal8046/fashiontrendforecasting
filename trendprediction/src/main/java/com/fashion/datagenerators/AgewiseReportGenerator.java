package com.fashion.datagenerators;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.fashion.services.DBConnector;


public class AgewiseReportGenerator 
{
	private int hitsgroup1;
	private int hitsgroup2;
	private int hitsgroup3;
	private int hitsgroup4;
	private int hitsgroup5;
	private String agegroup1;
	private String agegroup2;
	private String agegroup3;
	private String agegroup4;
	private String agegroup5;
	
	private int hits;
	private String agegroup;
	
	public AgewiseReportGenerator()
	{
		agegroup1="10-30";
		hitsgroup1=0;
		agegroup2="30-50";
		hitsgroup2=0;
		agegroup3="50-70";
		hitsgroup3=0;
		agegroup4="70-90";
		hitsgroup4=0;
		agegroup5="90-100";
		hitsgroup5=0;	
		
		agegroup="";
		hits=0;
	}
	
	public ArrayList<AgewiseReportGenerator> getAgewiseReport()
	{
		ArrayList<AgewiseReportGenerator> lst=new ArrayList<AgewiseReportGenerator>();
		
		try
		{
			String qr1="select count(*) as hits from trendsview where age between 10 and 30";			
			String qr2="select count(*) as hits from trendsview where age between 30 and 50";
			String qr3="select count(*) as hits from trendsview where age between 50 and 70";
			String qr4="select count(*) as hits from trendsview where age between 70 and 90";
			String qr5="select count(*) as hits from trendsview where age between 90 and 100";
			
			Connection con=DBConnector.connectDB();
			Statement st=con.createStatement();
						
			ResultSet rs=st.executeQuery(qr1);
			
			AgewiseReportGenerator obj;
			
			System.out.println(qr1);
			while(rs.next())
			{
				hitsgroup1=rs.getInt("hits");
				System.out.println(rs.getInt("hits"));
				
				obj=new AgewiseReportGenerator();
				obj.setAgegroup(agegroup1);
				obj.setHits(hitsgroup1);
				
				lst.add(obj);
			}
			
			System.out.println(qr2);
			rs=st.executeQuery(qr2);
			while(rs.next())
			{
				hitsgroup2=rs.getInt("hits");
				System.out.println(rs.getInt("hits"));
				
				obj=new AgewiseReportGenerator();
				obj.setAgegroup(agegroup2);
				obj.setHits(hitsgroup2);
				
				lst.add(obj);
			}
			
			System.out.println(qr3);
			rs=st.executeQuery(qr3);
			while(rs.next())
			{
				hitsgroup3=rs.getInt("hits");
				System.out.println(rs.getInt("hits"));
				
				obj=new AgewiseReportGenerator();
				obj.setAgegroup(agegroup3);
				obj.setHits(hitsgroup3);
				
				lst.add(obj);
			}
			
			System.out.println(qr4);
			rs=st.executeQuery(qr4);
			while(rs.next())
			{
				hitsgroup4=rs.getInt("hits");
				System.out.println(rs.getInt("hits"));
				
				obj=new AgewiseReportGenerator();
				obj.setAgegroup(agegroup4);
				obj.setHits(hitsgroup4);
				
				lst.add(obj);
			}
			
			System.out.println(qr5);
			rs=st.executeQuery(qr5);
			while(rs.next())
			{
				hitsgroup5=rs.getInt("hits");
				System.out.println(rs.getInt("hits"));
				
				obj=new AgewiseReportGenerator();
				obj.setAgegroup(agegroup5);
				obj.setHits(hitsgroup5);
				
				lst.add(obj);
			}
			
			st.close();
			con.close();
		}
		catch(Exception ex) {}
		
		return lst;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public void setAgegroup(String agegroup) {
		this.agegroup = agegroup;
	}

	public int getHits() {
		return hits;
	}

	public String getAgegroup() {
		return agegroup;
	}

	public int getHitsgroup1() {
		return hitsgroup1;
	}

	public int getHitsgroup2() {
		return hitsgroup2;
	}

	public int getHitsgroup3() {
		return hitsgroup3;
	}

	public int getHitsgroup4() {
		return hitsgroup4;
	}

	public int getHitsgroup5() {
		return hitsgroup5;
	}

	public String getAgegroup1() {
		return agegroup1;
	}

	public String getAgegroup2() {
		return agegroup2;
	}

	public String getAgegroup3() {
		return agegroup3;
	}

	public String getAgegroup4() {
		return agegroup4;
	}

	public String getAgegroup5() {
		return agegroup5;
	}
	
	
	
	
	
	
	

}
