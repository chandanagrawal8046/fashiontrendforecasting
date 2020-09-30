package com.fashion.services;


public class Mail 
{
	SendMail mail=null;
	
	public Mail()
    {
        String sendermail="projectgateway2019@outlook.com";
        String senderpass="Gateway@1234";
        mail=new SendMail(sendermail, senderpass);
    }
	
	public boolean accountActivation(String usernm,String userid,String pass,String email)
    {        
        try
        {            
            String msg=" Dear "+usernm+","+ "\n\n Your account request is approved successfully on Fashion Trends System.";
            msg+="\n\n Your login credentials are as follows\n Userid : "+userid+" \n Password : "+pass;
            msg+="\n\n Thank you.\n Fashion Trends Administrator.";
            mail.sendMail("Account Activation",msg, email);
            
            System.out.println("mail sent successfully..");
            return true;
            
        }
        catch(Exception ex){ 
        	System.out.println("mail : "+ex);
        	return false;}
    }
	
	public void passRecovery(String usernm,String pass,String email)
    {
        try
        {            
            String msg=" Dear "+usernm+","+ "\n\n Your password recovered successfully on Fashion Trends System.";
            msg+="\n Your new password is : "+pass+"\n\n Thank you.\n Fashion Trends Administrator.";
            mail.sendMail("Password Recovery",msg, email);
        }
        catch(Exception ex){}
    }
	
	
	public void finalizeOrder(String usernm,String orderno,double amount,String email)
    {
        try
        {            
            String msg=" Dear "+usernm+","+ "\n\n Your order [Order No : orderno] is placed successfully on Fashion Trends System.";
            msg+="\n Your total bill is Rs. : "+amount+"\n\n Thanks for choosing us.\n Fashion Trends Administrator.";
            mail.sendMail("Order Placed",msg, email);
        }
        catch(Exception ex){}
    }
	
}
