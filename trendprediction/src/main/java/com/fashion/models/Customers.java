package com.fashion.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.fashion.services.UserCredits;
import com.fashion.services.DBConnector;
import com.fashion.services.Mail;
import com.fashion.services.PredictionFuns;

public class Customers
{
	private int id;
	private String name;
	private String userid;
	private String gender;
	private String mobile;
	private String email;
	private String dob;
	private String secquestion;
	private String answer;
	private String message;
	private int age;
	
	public Customers()
	{
		id=0;
		age=0;
		name="";
		userid="";
		gender="";
		mobile="";
		email="";
		dob="";
		secquestion="";
		answer="";
		message="Member registration failed..";
	}
	
	public boolean submit()
	{
		boolean flag=false;
		Connection con;
		CallableStatement cst;
        int n=0;
		
		try
		{
			con=DBConnector.connectDB();
			System.out.println("Name : "+name);
			
			PredictionFuns obj=new PredictionFuns();
			int id=obj.FetchMax("customers", "id");
            if(id==1)
                id=2001;
            
            UserCredits credit=new UserCredits();
            String userid="",pass="";
            userid=credit.GetUserId(name, id);
            pass=credit.GetUserPassword(id);
            
            String arr[]=dob.split("-");
            for(String a : arr)
            	System.out.println(a);
            
            
            int yr=Integer.parseInt(arr[0]);
            
            Date dt=new Date();
            @SuppressWarnings("deprecation")
			int year=dt.getYear()+1900;
            int age=year-yr;
            
                        
            cst=con.prepareCall("{call customerreg(?,?,?,?,?,?,?,?,?,?,?)}");
            cst.setInt(1,id);
            cst.setString(2,name);
            cst.setString(3,mobile);            
            cst.setString(4,email);            
            cst.setString(5,dob);
            cst.setInt(6,age);
            cst.setString(7,gender);
            cst.setString(8,userid);
            cst.setString(9,secquestion);            
            cst.setString(10,answer);
            cst.setString(11,pass);
                        
            n=cst.executeUpdate();
            
            if(n>0)
            {               
                flag=true;  
                
                Mail mail=new Mail();
                mail.accountActivation(name, userid, pass, email);
                
            }
			
		}
		catch(Exception ex)
		{
			System.out.println("User error : "+ex);
		}
		
		return flag;			
	}
	
	public boolean update()
	{
		boolean flag=false;
		Connection con;
		CallableStatement cst;
        int n=0;
		
		try
		{
			con=DBConnector.connectDB();
			System.out.println("Name : "+name);
			
            String arr[]=dob.split("-");
            for(String a : arr)
            	System.out.println(a);
            
            int yr=Integer.parseInt(arr[0]);
            
            Date dt=new Date();
            @SuppressWarnings("deprecation")
			int year=dt.getYear()+1900;
            int age=year-yr;
            
                        
            cst=con.prepareCall("{call editcustomer(?,?,?,?,?,?,?,?,?)}");
            
            cst.setString(1,name);
            cst.setString(2,mobile);            
            cst.setString(3,email);            
            cst.setString(4,dob);
            cst.setInt(5,age);
            cst.setString(6,gender);
            cst.setString(7,secquestion);            
            cst.setString(8,answer);            
            cst.setString(9,userid);
                                    
            n=cst.executeUpdate();
            
            if(n>0)
            {               
                flag=true;                     
            }
			
		}
		catch(Exception ex)
		{
			System.out.println("User error : "+ex);
		}
		
		return flag;			
	}
	
	public ArrayList<Customers> getCustomersList()
	{
		ArrayList<Customers> obj=new ArrayList<Customers>();
		
		try
		{			
			Connection con=DBConnector.connectDB();
			Statement st=con.createStatement();
						
			ResultSet rs=st.executeQuery("select * from customerdetails where status='active'");
			Customers c;
			while(rs.next())
			{	
				
					c=new Customers();
					c.userid=rs.getString("userid");
					c.name=rs.getString("name");
					c.mobile=rs.getString("mobile");
					c.email=rs.getString("emailid");
					c.dob=rs.getString("dob");
					c.age=rs.getInt("age");
					c.gender=rs.getString("gender");
					obj.add(c);
					System.out.println(rs.getInt("id")+"  "+rs.getString("name")+"		"+rs.getString("userid"));
				
			}			
		}
		catch(Exception ex) {System.out.println("Error List : "+ex);}
		return obj;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	
	
}
