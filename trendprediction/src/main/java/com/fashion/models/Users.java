package com.fashion.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.fashion.services.DBConnector;
import com.fashion.services.Mail;
import com.fashion.services.UserCredits;



public class Users 
{
	private String userid;
	private String pass;
	private String newpass;
	private String question;
	private String answer;
	private String usertype;
	
	public Users()
	{
		userid="";
		pass="";
		usertype="";
		newpass="";
		question="";
		answer="";
	}
	
	public String updatePass()
	{
		String flag="fail";
		Connection con;
		CallableStatement cst;
        int n=0;
		
		try
		{
			flag=isValidUser();
			if(!flag.equals("unmatched") || !flag.equals("fail"))
			{
				con=DBConnector.connectDB();
	            cst=con.prepareCall("{call changepass(?,?)}");
	            
	            cst.setString(1,userid);
	            cst.setString(2,newpass);            
	                                    
	            n=cst.executeUpdate();
	            
	            if(n>0)
	            {               
	                flag="success";                     
	            }
			}
			
		}
		catch(Exception ex)
		{
			flag="fail";
			System.out.println("User error : "+ex);
		}
		
		return flag;			
	}
	
	
	public String isValidUser()
    {
		String flag="fail";
        System.out.println("Calling "+userid);        
        Connection con=null;
        Statement st;
        ResultSet rs; 
        try
        {
        	           
            con=DBConnector.connectDB();
            
            String qr="select * from users where userid='"+userid+"' and pswd='"+pass+"' and userstatus='active'";
            System.out.println("Query : "+qr);
            
            st=con.createStatement();            
            rs=st.executeQuery(qr);
            
            int n=0;
            
            while(rs.next())
            {            	
                n++;
                System.out.println("matched");
                flag=rs.getString("usertype");
            }
            
            if(n==0)
                flag="unmatch";
            
            st.close();
            con.close();
        }
        catch(Exception ex)
        {
        	flag="fail";
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNewpass() {
		return newpass;
	}

	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	
}
