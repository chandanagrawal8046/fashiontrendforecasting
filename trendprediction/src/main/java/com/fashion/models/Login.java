package com.fashion.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.fashion.services.DBConnector;



public class Login 
{
	private String userid;
	private String password;
	private String stat;
	
	public Login()
	{
		userid="";
		password="";
		stat="fail";
	}
	
	
	public String checkUser()
    {        
        Connection con=null;
        CallableStatement cst;
        ResultSet rs;
        String sts="",uid="",psw="",nm="";
        con=DBConnector.connectDB();
        try
        {
        	System.out.println(userid+" - "+password);
            cst=con.prepareCall("{call userauthentication(?,?)}");
            
            cst.setString(1, userid);
            cst.setString(2, password);
            
            rs=cst.executeQuery();
            while(rs.next())
            {    
                uid=rs.getString("userid");
                psw=rs.getString("pswd");
                sts=rs.getString("usertype");
                nm=rs.getString("usernm");
            }
            if(uid.equals(userid) && psw.equals(password))
            {
                stat=sts+"-"+nm;                      
            }
            else
                stat="fail";  
            
            System.out.println("Status : "+stat);
        }
        catch(Exception e)
        {                       
            stat=e.getMessage();
        }
        return(stat);
    }


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getStat() {
		return stat;
	}


	public void setStat(String stat) {
		this.stat = stat;
	}

	
	
	
}
