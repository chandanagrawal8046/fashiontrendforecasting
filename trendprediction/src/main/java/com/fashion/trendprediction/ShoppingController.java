package com.fashion.trendprediction;

import java.util.Date;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fashion.datagenerators.CustomersDetails;
import com.fashion.datagenerators.GetCartTotal;
import com.fashion.models.AlsoViewedProducts;
import com.fashion.models.BrowsingHistory;
import com.fashion.models.CartDetails;
import com.fashion.models.CartManager;
import com.fashion.models.Customers;
import com.fashion.models.Login;
import com.fashion.models.LoginRecorder;
import com.fashion.models.OrderDetails;
import com.fashion.models.Orders;
import com.fashion.models.ProductCategories;
import com.fashion.models.Products;
import com.fashion.models.Users;
import com.fashion.services.MachineInfo;
import com.fashion.services.Mail;

@Controller
public class ShoppingController 
{
	@RequestMapping("/start")
	public ModelAndView startShop(ProductCategories objcat,Products objprod,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		
		try
		{
		ArrayList<ProductCategories> lst=new ArrayList<ProductCategories>();
		lst=objcat.getReport();
		
		ArrayList<Products> lstp=new ArrayList<Products>();
		lstp=objprod.getAllProducts();
		
		mv.addObject("categories",lst);
		mv.addObject("products",lstp);
		mv.setViewName("Shop.jsp");
		
		CartManager obj=new CartManager();
        int orderno=obj.startCart();
        
        HttpSession session=request.getSession(true);
        session.setAttribute("orderno", String.valueOf(orderno));
        
		}
		catch(Exception ex) {}
		
		return mv;			
	}
	
	
	@RequestMapping("prodlist")
	public ModelAndView productsList(ProductCategories objcat,Products objprod,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		
		try
		{
			ArrayList<ProductCategories> lst=new ArrayList<ProductCategories>();
			lst=objcat.getReport();
			
			mv.addObject("categories",lst);			
			mv.setViewName("Shop.jsp");
		
		}
		catch(Exception ex) {}
		
		return mv;			
	}
	
	@RequestMapping("viewprod")
	public ModelAndView viewProductDetails(AlsoViewedProducts avp,HttpServletRequest request)
	{		
		ModelAndView mv=new ModelAndView();
		try
		{
			int prodid=Integer.parseInt(request.getParameter("prodid"));
			
			HttpSession session=request.getSession(true);
			session.setAttribute("prodid", prodid);          
	        System.out.println("Prod id : "+prodid);
	        
	        int orderno=0;
	        try
	        {
	        	orderno=Integer.parseInt(String.valueOf(session.getAttribute("orderno")));
	        }
	        catch(Exception ex)
	        {
	        	CartManager obj=new CartManager();
	            orderno=obj.startCart();
	            	            
	            session.setAttribute("orderno", String.valueOf(orderno));
	        }
	        
	        
	        
	        String sessionid=session.getId();
	        
	        MachineInfo minfo=new MachineInfo();
	        String address=minfo.getHostname()+" "+minfo.getIpaddress();
	        
	        BrowsingHistory track=new BrowsingHistory();
	        
	        track.setProdid(prodid);
	        track.setOrderno(orderno);
	        track.setHostip(address);
	        track.setSessionid(sessionid);
	        
	        track.track();
	        
	        ArrayList<AlsoViewedProducts> lst=new ArrayList<AlsoViewedProducts>();
	        avp.setProdid(prodid);
	        
	        lst=avp.getAlsoViewedReport();
	        System.out.println("Size : "+lst.size());
	        
	        mv.addObject("viewreport",lst);
			mv.setViewName("ViewProductDetails.jsp");
	       // mv.setViewName("Test.jsp");
        
		}
		catch(Exception ex) {}
		
		return mv;			
	}
	
	
	@RequestMapping("addtocart")
	public ModelAndView adToCart(HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		try
        {            
			HttpSession session=request.getSession(true);
            int orderno=Integer.parseInt(String.valueOf(session.getAttribute("orderno")));
            int prodid=Integer.parseInt(String.valueOf(session.getAttribute("prodid")));
            int quantity=Integer.parseInt(request.getParameter("quantity"));
            
            System.out.println("order no : "+orderno+"\t prodid : "+prodid);
            
            CartManager obj=new CartManager();
            obj.addToCart(orderno, prodid, quantity);
            
            ArrayList<CartDetails> lstcart=new ArrayList<CartDetails>();
            CartDetails dtl=new CartDetails();
            dtl.setOrderno(orderno);
            lstcart=dtl.getCartDetails();
            
            mv.addObject("cart",lstcart);
    		mv.setViewName("MyCart.jsp");
    		
    		GetCartTotal t=new GetCartTotal();
    		t.showCartTotal(orderno);
            double cartsum=t.getTotal();
    		double discount=t.getDiscount();
    		double grandtotal=t.getGrand();
            mv.addObject("cartsum", cartsum);
            mv.addObject("discount", discount);
            mv.addObject("grandtotal", grandtotal);
            mv.addObject("tax", t.getTax());
        }
        catch(Exception ex){System.out.println(ex);}
		
        return mv;
	}
	
	
	@RequestMapping("continue")
	public ModelAndView continueShopping(ProductCategories objcat,Products objprod,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		
		ArrayList<ProductCategories> lst=new ArrayList<ProductCategories>();
		lst=objcat.getReport();
		
		ArrayList<Products> lstp=new ArrayList<Products>();
		lstp=objprod.getAllProducts();
		
		mv.addObject("categories",lst);
		mv.addObject("products",lstp);
		mv.setViewName("Shop.jsp");
		
		return mv;			
	}
	
	@RequestMapping("delfromcart")
	public ModelAndView deleteFromCart(HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		try
        {            
			HttpSession session=request.getSession(true);
            int orderno=Integer.parseInt(String.valueOf(session.getAttribute("orderno")));            
            System.out.println("order no : "+orderno);
            
            int cartid=Integer.parseInt(request.getParameter("cartid"));
            
            CartManager obj=new CartManager();
            obj.delFromCart(cartid);
            
            
            ArrayList<CartDetails> lstcart=new ArrayList<CartDetails>();
            CartDetails dtl=new CartDetails();
            dtl.setOrderno(orderno);
            lstcart=dtl.getCartDetails();
            
            mv.addObject("cart",lstcart);            
    		mv.setViewName("MyCart.jsp");
    		
    		GetCartTotal t=new GetCartTotal();
    		t.showCartTotal(orderno);
            double cartsum=t.getTotal();
    		double discount=t.getDiscount();
    		double grandtotal=t.getGrand();
            mv.addObject("cartsum", cartsum);
            mv.addObject("discount", discount);
            mv.addObject("grandtotal", grandtotal);
            mv.addObject("tax", t.getTax());
        }
        catch(Exception ex){System.out.println(ex);}
		
        return mv;
	}
	
	@RequestMapping("createaccount")
	public String createAcc()
	{
		return "CreateAccount.jsp";
	}
	
	@RequestMapping("createacc")
	public ModelAndView accountCreate(Customers obj)
	{
		String result="Failure.jsp";
		String message="";
		ModelAndView mv=new ModelAndView();
		try {
        if(obj.submit())
        {
        	result="UserLogin.jsp";
        	message="Congrats.. you are registered successfully..";
        }
        else
        {        	
			result="Failure.jsp";
			message="Sorry.. Customer Registration Failed..";
			System.out.println(message);
        }
		}
		catch(Exception ex)
		{
			System.out.println("Error : "+ex);
			result="Failure.jsp";
			message="Error : "+ex;
		}

        mv.addObject("message", message);
		mv.setViewName(result);

        return mv;
	}
	
	@RequestMapping("chpass")
	public String chpass()
	{
		return "ChangePassword.jsp";
	}
	
	@RequestMapping("changepass")
	public ModelAndView changePass(Users obj,HttpServletRequest request)
	{
		String result="Failure.jsp";
		String message="";
		ModelAndView mv=new ModelAndView();
		try 
		{
			HttpSession session=request.getSession(true);
			obj.setUserid(String.valueOf(session.getAttribute("user")));
			
			String res=obj.updatePass();
	        if(res.equals("success"))
	        {
	        	result="Success.jsp";
	        	message="Password updated successfully..";
	        }
	        else if(res.equals("unmatched"))
	        {        	
				result="Failure.jsp";
				message="Sorry.. Invalid current password..";
				System.out.println(message);
	        }
	        else
	        {        	
				result="Failure.jsp";
				message="Sorry.. Password change failed..";
				System.out.println(message);
	        }
		}
		catch(Exception ex)
		{
			System.out.println("Error : "+ex);
			result="Failure.jsp";
			message="Error : "+ex;
		}

        mv.addObject("message", message);
		mv.setViewName(result);

        return mv;
	}
	
	@RequestMapping("userlogin")
	public String userLogin()
	{
		return "UserLogin.jsp";
	}
	
	@RequestMapping("userauth")
	public ModelAndView authenticate(Login u,HttpServletRequest request)
	{		
		ModelAndView mv=new ModelAndView();
	
		String viewName="Failure.jsp";
		String message="Sorry Authentication failed..";
		try
		{
			HttpSession session=request.getSession(true);
						
			String res=u.checkUser();
			if(!res.equals("fail"))
			{
				String userid=u.getUserid();
								
				String arr[]=res.split("-");
				String type=arr[0];
				String name=arr[1];
				
				String sessionid=session.getId();
				session.setAttribute("user", userid);
				session.setAttribute("utype", type);
				session.setAttribute("username", name);
				
				if(type.equals("customer"))		
					viewName="FinalizeOrder.jsp";
				
				LoginRecorder rec=new LoginRecorder();
				rec.loginLog(userid, res, sessionid);				
			}
			
		}
		catch(Exception ex)
		{
			System.out.println("Error : "+ex);
		}
		mv.addObject("message", message);
		mv.setViewName(viewName);
		
		return mv;
	}
	
	
	@RequestMapping("orderinfo")
	public String showOrderInfo()
	{
		return "OrderInfo.jsp";
	}
	
	
	@RequestMapping("finalizeorder")
	public ModelAndView finalizeOrder(HttpServletRequest request,Orders o)
	{
		ModelAndView mv=new ModelAndView();
		
		String viewName="Failure.jsp";
		String message="Sorry Authentication failed..";
		try
        {
			HttpSession session=request.getSession(true);
			
            String userid=String.valueOf(session.getAttribute("user"));
            int orderno=Integer.parseInt(String.valueOf(session.getAttribute("orderno")));
            String username=String.valueOf(session.getAttribute("username"));
            
            Date dt=new Date();
            int d=dt.getDate();
            int m=dt.getMonth()+1;
            int y=dt.getYear()+1900;

            int hr=dt.getHours();
            int mn=dt.getMinutes();
            int sc=dt.getSeconds();

            String today=d+"/"+m+"/"+y+" "+hr+":"+mn+":"+sc;
            System.out.println("Today : "+today);
            
            GetCartTotal ct=new GetCartTotal();
            ct.showCartTotal(orderno);
            
            double totalbill=ct.getGrand();
            System.out.println("Total Bill : "+totalbill);
            
            o.setOrderno(orderno);
            o.setUserid(userid);
            o.setOrderdt(today);
            o.setBill(totalbill);
            
            if(o.submit())
            {
            	viewName="Success.jsp";                
                message="Your order placed successfully..";
                
                CustomersDetails dtl=new CustomersDetails(userid);
                String email=dtl.getEmail();
                
                Mail mail=new Mail();
                mail.finalizeOrder(username, String.valueOf(orderno), totalbill, email);
            }            
        }
        catch(Exception ex){System.out.println("Order final : "+ex);}
		
		
		mv.addObject("message", message);
		mv.setViewName(viewName);
		
		return mv;
	}
	
	
	@RequestMapping("orderslist")
	public ModelAndView ordersList(HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		Orders obj=new Orders();
		String utype="",home="";
		try
		{
			HttpSession ses=request.getSession(true);
			String userid=String.valueOf(ses.getAttribute("user"));
			utype=String.valueOf(ses.getAttribute("utype"));
			
			if(utype.equals("customer")) {
				obj.setClause("where userid='"+userid+"'");
				home="MyOrdersList.jsp";
			}
			if(utype.equals("admin"))
				home="AllOrdersList.jsp";
		}
		catch(Exception ex) 
		{System.out.println("Not user : admin");}
		
		ArrayList<Orders> lst=new ArrayList<Orders>();
		lst=obj.getOrdersReport();
		
		mv.addObject("orders",lst);		
		mv.setViewName(home);
		
		return mv;			
	}
	
	@RequestMapping("orderdetails")
	public ModelAndView orderDetails(OrderDetails obj)
	{
		ModelAndView mv=new ModelAndView();
				
		ArrayList<OrderDetails> lst=new ArrayList<OrderDetails>();
		lst=obj.getOrderDetails();
		
		mv.addObject("orderdetails",lst);		
		mv.setViewName("ShowOrderDetails.jsp");
		
		return mv;		
	}
	
	@RequestMapping("processorder")
	public ModelAndView ProcessOrder(Orders obj)
	{
		String result="Failure.jsp";
		String message="";
		ModelAndView mv=new ModelAndView();
		try {
        if(obj.updateOrderStatus())
        {
        	result="Success.jsp";
        	message="Congrats.. Order "+obj.getAct()+"ed successfully..";
        }
        else
        {        	
			result="Failure.jsp";
			message="Sorry.. Order processing Failed..";
			System.out.println(message);
        }
		}
		catch(Exception ex)
		{
			System.out.println("Error : "+ex);
			result="Failure.jsp";
			message="Error : "+ex;
		}

        mv.addObject("message", message);
		mv.setViewName(result);

        return mv;
	}
	
	
	@RequestMapping("cancelorder")
	public ModelAndView CancelOrder(Orders obj)
	{
		String result="Failure.jsp";
		String message="";
		ModelAndView mv=new ModelAndView();
		try {
        if(obj.cancelOrder())
        {
        	result="Success.jsp";
        	message="Congrats.. Order cancelled successfully..";
        }
        else
        {        	
			result="Failure.jsp";
			message="Sorry.. Order cancellation failed..";
			System.out.println(message);
        }
		}
		catch(Exception ex)
		{
			System.out.println("Error : "+ex);
			result="Failure.jsp";
			message="Sorry.. Order cancellation failed..";
		}

        mv.addObject("message", message);
		mv.setViewName(result);

        return mv;
	}
}
