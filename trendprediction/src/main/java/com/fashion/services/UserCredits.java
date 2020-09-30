package com.fashion.services;

public class UserCredits 
{
		public String GetUserId(String name,int id)
	   {
	       String userid = "";
	       try
	       {
	           int mid = id * 3;
	           userid = name.substring(0, 3).toLowerCase()+"@" + Math.round(mid / 3.0);
	       }
	       catch(Exception ex){}
	       return userid;
	   }

	   public String GetUserPassword(int id)
	   {
	       String pass = "";
	       try
	       {
	           pass = "FTP!" + (id * 41);
	       }
	       catch(Exception ex){}
	       return pass;
	   }
	   
	   public String GetRecPass(String mobile,String name)
	   {
	       String pass = "";
	       try
	       {
	           System.out.println(mobile);
	           System.out.println(name);
	           int x= Integer.parseInt(mobile.substring(8));
	           System.out.println("X : "+x);
	           pass = name.substring(1, 5)+"!"+(x*37);
	           if(pass.contains(" "))
	               pass=pass.replace(" ", "P");
	           
	           System.out.println("New Password : "+pass);
	       }
	       catch(Exception ex){}
	       return pass;
	   }
	   
	   public String GetCompanyId(String name,int id)
	   {
	       String userid = "";
	       try
	       {
	           int mid = id * 3;
	           userid = name.substring(0, 2).toLowerCase()+"@" + Math.round(mid / 3.0);
	       }
	       catch(Exception ex){}
	       return userid;
	   }

	   public String GetCompanyPassword(int id)
	   {
	       String pass = "";
	       try
	       {
	           pass = "CMP!" + (id * 47);
	       }
	       catch(Exception ex){}
	       return pass;
	   }

}
