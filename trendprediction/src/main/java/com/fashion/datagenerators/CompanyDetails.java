package com.fashion.datagenerators;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.fashion.services.DBConnector;

public class CompanyDetails 
{
	private String message;
	private String password;
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
	
	
	public CompanyDetails(String userid)
	{
		try
		{
			Connection con=DBConnector.connectDB();
			Statement st=con.createStatement();
						
			String qr="select * from companiesdetails where status='active' and userid='"+userid+"'";
			System.out.println("Query : "+qr);
			ResultSet rs=st.executeQuery(qr);

			while(rs.next())
			{
				id=rs.getInt("id");
				name=rs.getString("name");
				mobile=rs.getString("mobile");
				email=rs.getString("emailid");
				compurl=rs.getString("compurl");
				address=rs.getString("address");
				headoffice=rs.getString("headoffice");
				userid=rs.getString("userid");
				secquestion=rs.getString("secques");
				answer=rs.getString("answer");
				password=rs.getString("pswd");
				status=rs.getString("status");
				System.out.println(rs.getInt("id")+"  "+rs.getString("name"));
			}	
		}
		catch(Exception ex) {System.out.println("Error generator : "+ex);}
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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

	
	
	
	
}
