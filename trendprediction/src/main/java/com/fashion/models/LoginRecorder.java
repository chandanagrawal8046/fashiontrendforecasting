package com.fashion.models;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.fashion.services.DBConnector;

public class LoginRecorder 
{
	Connection con;
    PreparedStatement pst;
    
    public LoginRecorder()
    {
        con=DBConnector.connectDB();
    }
    
    public void loginLog(String userid,String utype,String sessionid)
    {
        try
        {
            pst=con.prepareStatement("insert into loginrecorder values(default,?,?,default,?)");
                       
            pst.setString(1,userid);
            pst.setString(2,utype);
            pst.setString(3,sessionid);
            
            pst.executeUpdate();     
            System.out.println("Log inserted..");
        }
        catch(Exception ex){System.out.println(ex);}
    }

}
