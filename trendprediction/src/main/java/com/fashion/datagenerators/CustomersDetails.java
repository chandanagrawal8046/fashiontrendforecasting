package com.fashion.datagenerators;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.fashion.services.DBConnector;


public class CustomersDetails 
{
	private int id;
	private String name;
	private String userid;
	private String gender;
	private String mobile;
	private String email;
	private String dob;
	private int age;
	private String secquestion;
	private String answer;
	private String message;
	private String password;
	
	public CustomersDetails(String userid)
	{
		try
		{
			Connection con=DBConnector.connectDB();
			Statement st=con.createStatement();
						
			String qr="select * from customerdetails where status='active' and userid='"+userid+"'";
			System.out.println("Query : "+qr);
			ResultSet rs=st.executeQuery(qr);
			
			while(rs.next())
			{
				id=rs.getInt("id");
				name=rs.getString("name");
				mobile=rs.getString("mobile");
				email=rs.getString("emailid");
				dob=rs.getString("dob");
				age=rs.getInt("age");				
				gender=rs.getString("gender");
				secquestion=rs.getString("secques");
				answer=rs.getString("answer");
				password=rs.getString("pswd");

				System.out.println(rs.getInt("id")+"  "+rs.getString("name")+"		"+rs.getString("gender"));
			}	
		}
		catch(Exception ex) {System.out.println("Error generator : "+ex);}
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
