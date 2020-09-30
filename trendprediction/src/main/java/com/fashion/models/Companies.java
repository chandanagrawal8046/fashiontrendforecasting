package com.fashion.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.fashion.services.UserCredits;
import com.fashion.services.DBConnector;
import com.fashion.services.Mail;
import com.fashion.services.PredictionFuns;

public class Companies 
{	
	private String compurl;	
	private String status;
	private int id;
	private String name;
	private String userid;
	private String mobile;
	private String email;
	private String address;
	private String headoffice;
	private String secquestion;
	private String answer;
	private int age;
	
	public Companies()
	{
		id=0;		
		name="";
		userid="";
		compurl="";
		mobile="";
		email="";
		headoffice="";
		address="";
		secquestion="";
		answer="";
		status="";
	}
	
	public boolean newCompany()
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
			int id=obj.FetchMax("companies", "id");
            if(id==1)
                id=6001;
            
            UserCredits credit=new UserCredits();
            String userid="",pass="";
            userid=credit.GetCompanyId(name, id);
            pass=credit.GetCompanyPassword(id);
                        
            cst=con.prepareCall("{call companyreg(?,?,?,?,?,?,?,?,?,?,?)}");
            cst.setInt(1,id);
            cst.setString(2,name);
            cst.setString(3,mobile);            
            cst.setString(4,email);            
            cst.setString(5,compurl);
            cst.setString(6,address);
            cst.setString(7,headoffice);
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
	
	public boolean updateCompany()
	{
		boolean flag=false;
		Connection con;
		CallableStatement cst;
        int n=0;
		
		try
		{
			
			System.out.println("Name : "+name);
			System.out.println("Name : "+name);
			System.out.println("Name : "+mobile);            
			System.out.println("Name : "+email);            
			System.out.println("Name : "+compurl);
			System.out.println("Name : "+address);
			System.out.println("Name : "+headoffice);
			System.out.println("Name : "+secquestion);            
			System.out.println("Name : "+answer);            
			System.out.println("Name : "+userid);
			
			con=DBConnector.connectDB();
            cst=con.prepareCall("{call editcompany(?,?,?,?,?,?,?,?,?)}");
            
            cst.setString(1,name);
            cst.setString(2,mobile);            
            cst.setString(3,email);            
            cst.setString(4,compurl);
            cst.setString(5,address);
            cst.setString(6,headoffice);
            cst.setString(7,secquestion);            
            cst.setString(8,answer);            
            cst.setString(9,userid);
                                    
            n=cst.executeUpdate();
            System.out.println("Updated : "+n);
            
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
	
	
	public ArrayList<Companies> getCompaniesList()
	{
		ArrayList<Companies> obj=new ArrayList<Companies>();
		
		try
		{			
			Connection con=DBConnector.connectDB();
			Statement st=con.createStatement();
						
			ResultSet rs=st.executeQuery("select * from companiesdetails where status='active'");
			Companies c;
			while(rs.next())
			{	
				
					c=new Companies();
					c.userid=rs.getString("userid");
					c.name=rs.getString("name");
					c.mobile=rs.getString("mobile");
					c.email=rs.getString("emailid");
					c.compurl=rs.getString("compurl");
					c.headoffice=rs.getString("headoffice");
					c.address=rs.getString("address");
					obj.add(c);
					System.out.println(rs.getInt("id")+"  "+rs.getString("name")+"		"+rs.getString("userid"));
				
			}			
		}
		catch(Exception ex) {System.out.println("Error List : "+ex);}
		return obj;
	}

	public String getCompurl() {
		return compurl;
	}

	public void setCompurl(String compurl) {
		this.compurl = compurl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHeadoffice() {
		return headoffice;
	}

	public void setHeadoffice(String headoffice) {
		this.headoffice = headoffice;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	

}
