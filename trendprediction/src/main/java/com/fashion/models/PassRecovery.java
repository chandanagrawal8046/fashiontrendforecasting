package com.fashion.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.fashion.services.DBConnector;
import com.fashion.services.Mail;
import com.fashion.services.UserCredits;

public class PassRecovery 
{
	private String userid;
	private String secquestion;
	private String answer;
	
	public PassRecovery()
	{
		userid="";
		secquestion="";
		answer="";		
	}
	
	private String getUserType()
	{
		String usertype="NA";
		Connection con=null;
        Statement st;
        ResultSet rs;
		
		try
		{
			con=DBConnector.connectDB();
            
            String qr="select usertype from users where userid='"+userid+"' and userstatus='active'";
            System.out.println("Query : "+qr);
            
            st=con.createStatement();            
            rs=st.executeQuery(qr);
            
            while(rs.next())
            {
            	usertype=rs.getString("usertype");
            	System.out.println("matched : "+usertype);
            }
            
            rs.close();
            st.close();
            con.close();
		}
		catch(Exception ex) 
		{
			usertype="NA";
			System.out.println("Error : "+ex);
		}
		
		return usertype;
	}
	
	private ArrayList<String> isUser()
    {
		ArrayList<String> arr=new ArrayList<String>();
        System.out.println("Calling "+userid);        
        Connection con=null;
        Statement st;
        ResultSet rs; 
        try
        {
        	String usertype=getUserType();
        	String table="";
        	
        	if(!usertype.equals("NA") || !usertype.equals("admin"))
        	{
        		if(usertype.equals("company"))
        			table="companies";
        		else if(usertype.equals("customer"))
        			table="customers";
        		           
	            con=DBConnector.connectDB();
	            
	            String qr="select * from "+table+" where userid='"+userid+"' and secques='"+secquestion+"' and answer='"+answer+"'";
	            System.out.println("Query : "+qr);
	            
	            st=con.createStatement();            
	            rs=st.executeQuery(qr);
	            
	            int n=0;
	            
	            while(rs.next())
	            {
	            	String mobile=rs.getString("mobile");
	            	String email=rs.getString("emailid");
	            	String name=rs.getString("name");
	            	
	            	arr.add(name);
	            	arr.add(mobile);
	            	arr.add(email);
	            	
	                n++;
	                System.out.println("matched");
	            }
	            
	            if(n==0)
	                arr.add("NA");
	            
	            st.close();
	            con.close();
        	}
        }
        catch(Exception ex)
        {
        	arr.add("NA");
            System.out.println(ex.getMessage());
        }
        
        return arr;
    }
	
    
    public boolean recoverPass()
    {
    	boolean flag=false;    	
        Connection con=null;
        PreparedStatement pst;
        try
        {   
        	ArrayList<String> arr=new ArrayList<String>();
        	arr=isUser();
        	
        	String sts=String.valueOf(arr.get(0));
        	
            if(!sts.equals("NA"))
            {
            	String name=String.valueOf(arr.get(0));
            	String mobile=String.valueOf(arr.get(1));
            	String email=String.valueOf(arr.get(2));
            	System.out.println("Qr : "+name+"	"+mobile+"	"+email);
            	
            	UserCredits credit=new UserCredits();
            	String newpass=credit.GetRecPass(mobile, name);
            	System.out.println("Qr : "+name+"	"+mobile+"	"+email+"	"+newpass);
            	
                String qr="update users set pswd='"+newpass+"' where userid='"+userid+"'";
                
                con=DBConnector.connectDB();
                pst=con.prepareStatement(qr);
                System.out.println(qr);    
              
                if(pst.executeUpdate()>0)
                {
                	Mail mail=new Mail();
                	mail.passRecovery(name, newpass, email);
                	
                    flag=true;
                    System.out.println("pass changed..");
                }
                else
                    flag=false;
            }
        }
        catch(Exception ex)
        {
            flag=false;
            System.out.println(ex.getMessage());
        }
        return flag;
    }

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSecquestion() {
		return secquestion;
	}

	public void setSecquestion(String secquestion) {
		this.secquestion = secquestion;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

    
    
}
