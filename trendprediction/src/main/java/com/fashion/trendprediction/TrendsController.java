package com.fashion.trendprediction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fashion.models.BrowsingHistory;
import com.fashion.models.Companies;
import com.fashion.models.Customers;
import com.fashion.models.Login;
import com.fashion.models.LoginRecorder;
import com.fashion.models.PassRecovery;
import com.fashion.models.ProductCategories;
import com.fashion.models.Products;
import com.fashion.models.Wishlist;
import com.fashion.services.MachineInfo;
import com.fashion.services.PredictionFuns;

@Controller
public class TrendsController 
{
	@RequestMapping("trend")
	public ModelAndView welcome()
	{
		ModelAndView mv=new ModelAndView();
		try
		{
			MachineInfo info=new MachineInfo();			
			String host=info.getHostname();
			
			ArrayList<BrowsingHistory> lst=new ArrayList<BrowsingHistory>();
			BrowsingHistory obj=new BrowsingHistory();
			obj.setHostip(host);
			
			lst=obj.getHistory();
			
			mv.addObject("history", lst);
			mv.setViewName("index.jsp");
			
		}
		catch(Exception ex) {}
		
		return mv;
	}
	
	@RequestMapping("login")
	public String login()
	{
		return "Login.jsp";
	}

	@RequestMapping("auth")
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
				
				if(type.equals("admin"))		
					viewName="TrendsAdmin.jsp";
				else if(type.equals("customer"))
				{
					ArrayList<BrowsingHistory> lst=new ArrayList<BrowsingHistory>();
					BrowsingHistory obj=new BrowsingHistory();
					obj.setUserid(userid);
					
					lst=obj.getRemovedProdHistory();
					
					mv.addObject("history", lst);
					
					viewName="CustomerHome.jsp";
				}
				else if(type.equals("company"))
					viewName="CompanyHome.jsp";
				
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
	
	@RequestMapping("adminhome")
	public String adminHome()
	{
		return "TrendsAdmin.jsp";
	}
	
	@RequestMapping("companyhome")
	public String companyHome()
	{
		return "CompanyHome.jsp";
	}
	
	@RequestMapping("userhome")
	public ModelAndView userHome(HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		try
		{
			HttpSession session=request.getSession(true);
			String userid=String.valueOf(session.getAttribute("user"));
			
			ArrayList<BrowsingHistory> lst=new ArrayList<BrowsingHistory>();
			BrowsingHistory obj=new BrowsingHistory();
			obj.setUserid(userid);
			
			lst=obj.getRemovedProdHistory();
			
			mv.addObject("history", lst);
			mv.setViewName("CustomerHome.jsp");
			
		}
		catch(Exception ex) {}
		
		return mv;
		
	}
	
	@RequestMapping("logout")
	public String logout()
	{
		return "Logout.jsp";
	}
	
	@RequestMapping("invalid")
	public String expireSession()
	{
		return "Invalidate.jsp";
	}

	@RequestMapping("register")
	public String registerUser()
	{
		return "NewCustomer.jsp";
	}
	
	@RequestMapping("custprofile")
	public String customerProfile(HttpServletRequest request)
	{		
		return "CustomerProfile.jsp";
	}
	
	@RequestMapping("regcustomer")
	public ModelAndView registerCust(Customers obj)
	{
		String result="Failure.jsp";
		String message="";
		ModelAndView mv=new ModelAndView();
		try {
        if(obj.submit())
        {
        	result="Success.jsp";
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
	
	@RequestMapping("editcustomer")
	public ModelAndView updateMember(Customers obj,HttpServletRequest request)
	{
		String result="Failure.jsp";
		String message="";
		ModelAndView mv=new ModelAndView();
		try {
			
			HttpSession session=request.getSession(true);
			String userid=String.valueOf(session.getAttribute("user"));
			
			obj.setUserid(userid);
			
        if(obj.update())
        {
        	result="Success.jsp";
        	message="Profile updated successfully..";
        }
        else
        {        	
			result="Failure.jsp";
			message="Sorry.. Profile update failed..";
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
	
	@RequestMapping("customerslist")
	public ModelAndView registerdMembers(Customers obj,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		
		ArrayList<Customers> lst=new ArrayList<Customers>();
		lst=obj.getCustomersList();
		
		mv.addObject("customers",lst);		
		mv.setViewName("CustomersList.jsp");
		
		return mv;		
	}
	
	
	//---------------------------------Companies-------------------------------------
	
	@RequestMapping("newcomp")
	public String newCompany()
	{
		return "NewCompany.jsp";
	}
	
	
	@RequestMapping("regcompany")
	public ModelAndView registerCompany(Companies obj)
	{
		String result="Failure.jsp";
		String message="";
		ModelAndView mv=new ModelAndView();
		try {
        if(obj.newCompany())
        {
        	result="Success.jsp";
        	message="Congrats.. New company registered successfully..";
        }
        else
        {        	
			result="Failure.jsp";
			message="Sorry.. Company Registration Failed..";
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
	
	@RequestMapping("complist")
	public ModelAndView companiesList(Companies obj)
	{
		ModelAndView mv=new ModelAndView();
		
		ArrayList<Companies> lst=new ArrayList<Companies>();
		lst=obj.getCompaniesList();
		
		mv.addObject("companies",lst);		
		mv.setViewName("CompaniesList.jsp");
		
		return mv;		
	}
	
	
	@RequestMapping("compprofile")
	public String companyProfile()
	{		
		return "CompanyProfile.jsp";
	}
	
	@RequestMapping("editcompany")
	public ModelAndView updateCompany(Companies obj,HttpServletRequest request)
	{
		String result="Failure.jsp";
		String message="";
		ModelAndView mv=new ModelAndView();
		try {
			
			HttpSession session=request.getSession(true);
			String userid=String.valueOf(session.getAttribute("user"));
			
			obj.setUserid(userid);
			
        if(obj.updateCompany())
        {
        	result="Success.jsp";
        	message="Congrats.. Company profile updated successfully..";
        }
        else
        {        	
			result="Failure.jsp";
			message="Sorry.. Company profile update failed..";
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
	
	
	@RequestMapping("forgot")
	public String forgotPass()
	{
		return "RecoverPassword.jsp";
	}
	
	@RequestMapping("recover")
	public ModelAndView recoverPassword(PassRecovery obj)
	{
		String result="Failure.jsp";
		String message="";
		ModelAndView mv=new ModelAndView();
		try {
        if(obj.recoverPass())
        {
        	result="Success.jsp";
        	message="Congrats.. Password recovered successfully.. New password is sent on registered emailid..";
        	
        	
        }
        else
        {        	
			result="Failure.jsp";
			message="Sorry.. Password Recovery Failed..";
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
	
	//-------------------------------Product Categories------------------------------
	
	@RequestMapping("newcategory")
	public ModelAndView registerCat(ProductCategories obj)
	{
		ModelAndView mv=new ModelAndView();
		
		ArrayList<ProductCategories> lst=new ArrayList<ProductCategories>();
		lst=obj.getReport();
		
		mv.addObject("categories",lst);		
		mv.setViewName("NewProdCategory.jsp");
		
		return mv;		
	}
	
	@RequestMapping("regcategory")
	public ModelAndView registerCategory(ProductCategories obj)
	{
		System.out.println("Calling Reg..");
		String result="Failure.jsp";
		String message="";
		ModelAndView mv=new ModelAndView();
		try 
		{
			
        if(obj.addNewCategory())
        {
        	result="Success.jsp";
        	message="Congrats.. New product category registered successfully..";
        }
        else
        {        	
			result="Failure.jsp";
			message="Sorry.. New product category registration failed..";
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
	
	@RequestMapping("categories")
	public ModelAndView categoriesList(ProductCategories obj)
	{
		ModelAndView mv=new ModelAndView();
		
		ArrayList<ProductCategories> lst=new ArrayList<ProductCategories>();
		lst=obj.getReport();
		
		mv.addObject("categories",lst);		
		mv.setViewName("ProdCategoriesList.jsp");
		
		return mv;		
	}
	
	@RequestMapping("deleteCat")
	public ModelAndView delGroup(ProductCategories obj,HttpServletRequest request)
	{
		String result="Failure.jsp";
		String message="Product category deletion failed..";
		ModelAndView mv=new ModelAndView();
		try 
		{
			obj.setCatid(Integer.parseInt(request.getParameter("cid")));
			
        if(obj.deleteCategory())
        {
        	result="Success.jsp";
        	message="Product category deleted successfully..";
        }
        else
        {        	
			result="Failure.jsp";
			message="Product category deletion failed..";
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
	
	
	//----------------------------------Products---------------------------------
	
	@RequestMapping("newProduct")
	public ModelAndView regProd(ProductCategories obj,Companies comp)
	{
		ModelAndView mv=new ModelAndView();
		
		ArrayList<ProductCategories> lst=new ArrayList<ProductCategories>();
		lst=obj.getReport();
		
		ArrayList<Companies> lstc=new ArrayList<Companies>();
		lstc=comp.getCompaniesList();
		
		mv.addObject("categories",lst);
		mv.addObject("companies",lstc);
		mv.setViewName("NewProduct.jsp");
		
		return mv;		
	}
	
	
	@RequestMapping("regproduct")
	public ModelAndView registerProduct(Products obj,HttpServletRequest request,@RequestParam("file") MultipartFile file)
	{
		System.out.println("Calling Reg..");
		String result="Failure.jsp";
		String message="Sorry.. New product registration failed..";
		ModelAndView mv=new ModelAndView();
		try 
		{
			if (!file.isEmpty()) 
	        {			
				PredictionFuns pf=new PredictionFuns(); 
	        	int maxid=pf.FetchMax("products", "id");
	        	if(maxid==1 || maxid==1001)
	        		maxid=6110;
			
	        	try
	        	{
	        		String filePath = request.getServletContext().getRealPath("/").concat("Products");                
	                System.out.println("Image Location:" + filePath);
	                
	                File dir=new File(filePath);
	                if(!dir.exists())
	                    dir.mkdirs();
	                
	                int ind=file.getOriginalFilename().lastIndexOf(".");                
	                String ext=file.getOriginalFilename().substring(ind+1);        
	                String newfileName="prod"+maxid+"."+ext; 
	                
	                File oldfile=new File(filePath+"/" + newfileName);
	                if(oldfile.exists()) {
	                	oldfile.delete();
	                	System.out.println("Old File deleted.."+newfileName);
	                }
	                
	                // Get the file and save it somewhere
	                byte[] bytes = file.getBytes();
	                Path path = Paths.get(filePath+"/" + newfileName);
	                Files.write(path, bytes);

	                System.out.println("You have successfully uploaded '" + file.getOriginalFilename() + "' - "+newfileName);
	                
	                obj.setId(maxid);
	                obj.setImgurl("Products/"+newfileName);
	                
	                if(obj.addProduct())
			        {
			        	result="Success.jsp";
			        	message="Congrats.. New product registered successfully..";
			        }
			        else
			        {        	
						result="Failure.jsp";
						message="Sorry.. New product registration failed..";
						System.out.println(message);
			        }
	        	}
	        	catch(IOException e)
	        	{
	        		System.out.println("Error : "+e);
	    			result="Failure.jsp";
	    			message="IO Error : "+e;
	        	}	        	
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
	
	
	@RequestMapping("catProducts")
	public ModelAndView catProds(Products obj,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		
		obj.setCategories(request.getParameter("cat"));
		HttpSession ses=request.getSession(true);
		
		String utype=String.valueOf(ses.getAttribute("utype"));
		obj.setBrand(String.valueOf(ses.getAttribute("username")));
		
		ArrayList<Products> lst=new ArrayList<Products>();
		if(utype.equals("company"))
			lst=obj.getCompanyCatProducts();
		else
			lst=obj.getCatProducts();
		
		mv.addObject("catproducts",lst);		
		mv.setViewName("CategorywiseProducts.jsp");
		
		return mv;		
	}
	
	@RequestMapping("viewcatprods")
	public ModelAndView catProds(ProductCategories obj)
	{
		ModelAndView mv=new ModelAndView();
		
		ArrayList<ProductCategories> lst=new ArrayList<ProductCategories>();
		lst=obj.getReport();
		
		mv.addObject("categories",lst);		
		mv.setViewName("ViewProductsList.jsp");
		
		return mv;		
	}
	
	
	@RequestMapping("myproducts")
	public ModelAndView companyProds(ProductCategories obj,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		
		HttpSession ses=request.getSession(true);
		String company=String.valueOf(ses.getAttribute("username"));
		
		ArrayList<ProductCategories> lst=new ArrayList<ProductCategories>();
		lst=obj.getCompanyProdCategories(company);
		
		mv.addObject("categories",lst);		
		mv.setViewName("CompanyProductsList.jsp");
		
		return mv;		
	}
	
	
//	--------------------------Wishlist------------------------------
	
	@RequestMapping("addtowishlist")
	public ModelAndView addToWishlist(Wishlist obj,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();		
		HttpSession ses=request.getSession(true);
		int prodid=0;
		
		String userid="",home="";
		try
		{
			prodid=Integer.parseInt(request.getParameter("prodid"));
			userid=String.valueOf(ses.getAttribute("user"));
			
		}
		catch(Exception ex) {}
		
		System.out.println("Prodid : "+prodid+"     userid : "+userid);
		
		if(ses.getAttribute("user")==null)
		{
			home="LoginWishlist.jsp";
			mv.setViewName(home);
			mv.addObject("prodid",prodid);
		}
		else
		{
			obj.setUserid(userid);
			obj.setProdid(prodid);
			
			if(obj.createWishlist())
			{
				ArrayList<Wishlist> lst=new ArrayList<Wishlist>();
				lst=obj.getMyWishlist();
				
				mv.addObject("wishlist",lst);		
				mv.setViewName("MyWishList.jsp");
			}
			else
			{
				mv.setViewName("Failure.jsp");
				String message="Sorry.. New product registration failed..";
				mv.addObject("message", message);
				System.out.println(message);
			}
		}
		
		
		
		return mv;		
	}
	
	@RequestMapping("wishlistauth")
	public ModelAndView wishauthenticate(Login u,HttpServletRequest request)
	{		
		ModelAndView mv=new ModelAndView();
	
		String viewName="Failure.jsp";
		
		try
		{
			HttpSession session=request.getSession(true);
			
			int prodid=Integer.parseInt(request.getParameter("prodid"));
						
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
				session.setAttribute("prodid", prodid);
				
				if(type.equals("customer"))		
					viewName="SendToWishList.jsp";
				
				LoginRecorder rec=new LoginRecorder();
				rec.loginLog(userid, res, sessionid);				
			}
			
		}
		catch(Exception ex)
		{
			System.out.println("Error : "+ex);
		}
				
		mv.setViewName(viewName);
		
		return mv;
	}
	
	
	
	@RequestMapping("createwishlist")
	public ModelAndView createWish(Wishlist obj,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		
		HttpSession ses=request.getSession(true);
		String userid=String.valueOf(ses.getAttribute("user"));
		
		obj.setUserid(userid);
		obj.setProdid(Integer.parseInt(request.getParameter("prodid")));
		
		if(obj.createWishlist())
		{
			ArrayList<Wishlist> lst=new ArrayList<Wishlist>();
			lst=obj.getMyWishlist();
			
			mv.addObject("wishlist",lst);		
			mv.setViewName("MyWishList.jsp");
		}
		else
		{
			mv.setViewName("Failure.jsp");
			String message="Sorry.. Unable to add to wishlist.. Try again";
			mv.addObject("message", message);
			System.out.println(message);
		}
		
		return mv;		
	}
	
	@RequestMapping("deletefromwish")
	public ModelAndView deleteFromWishlist(Wishlist obj,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		
		HttpSession ses=request.getSession(true);
		String userid=String.valueOf(ses.getAttribute("user"));
		
		obj.setUserid(userid);
		
		if(obj.removeFromWishlist())
		{
			ArrayList<Wishlist> lst=new ArrayList<Wishlist>();
			lst=obj.getMyWishlist();
			
			mv.addObject("wishlist",lst);		
			mv.setViewName("MyWishList.jsp");
		}
		else
		{
			mv.setViewName("Failure.jsp");
			String message="Sorry.. Unable to remove from wishlist.. Try again";
			mv.addObject("message", message);
			System.out.println(message);
		}
		
		return mv;		
	}
	
	@RequestMapping("mywishlist")
	public ModelAndView myWish(Wishlist obj,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		
		HttpSession ses=request.getSession(true);
		String userid=String.valueOf(ses.getAttribute("user"));
		
		obj.setUserid(userid);
		ArrayList<Wishlist> lst=new ArrayList<Wishlist>();
		lst=obj.getMyWishlist();
			
		mv.addObject("wishlist",lst);		
		mv.setViewName("MyWishList.jsp");
		
		return mv;		
	}
	
	
	@RequestMapping("categoryProducts")
	public ModelAndView categoryProds(Products obj,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		
		obj.setCategories(request.getParameter("cat"));
				
		ArrayList<Products> lst=new ArrayList<Products>();
		lst=obj.getCatProducts();
		
		mv.addObject("products",lst);		
		mv.setViewName("ShowCategoryProducts.jsp");
		
		return mv;		
	}
	
	
	

}
