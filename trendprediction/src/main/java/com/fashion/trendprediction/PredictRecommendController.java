package com.fashion.trendprediction;



import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fashion.datagenerators.AgewiseReportGenerator;
import com.fashion.datagenerators.GenderwiseReportGeneration;
import com.fashion.datagenerators.TopBrands;
import com.fashion.datagenerators.TopCategories;
import com.fashion.datagenerators.TopSoldProducts;
import com.fashion.models.ProfilewiseRecommendation;
import com.fashion.models.Recommendations;




@Controller
public class PredictRecommendController 
{
	@RequestMapping("agewise")
	public ModelAndView agewiseList()
	{
		ModelAndView mv=new ModelAndView();
		
		try
		{
			AgewiseReportGenerator obj=new AgewiseReportGenerator();
			ArrayList<AgewiseReportGenerator> lst=new ArrayList<AgewiseReportGenerator>();
			lst=obj.getAgewiseReport();
			
			mv.addObject("agereport",lst);
			mv.setViewName("AgewiseReport.jsp");
		
		}
		catch(Exception ex) {}
		
		return mv;			
	}
	
	@RequestMapping("genderwise")
	public ModelAndView genderwiseList()
	{
		ModelAndView mv=new ModelAndView();
		
		try
		{
			GenderwiseReportGeneration obj=new GenderwiseReportGeneration();
			ArrayList<GenderwiseReportGeneration> lst=new ArrayList<GenderwiseReportGeneration>();
			lst=obj.getGenderwiseReport();
			
			mv.addObject("genderreport",lst);
			mv.setViewName("GenderwiseReport.jsp");
		
		}
		catch(Exception ex) {}
		
		return mv;			
	}

	@RequestMapping("recommend")
	public ModelAndView recommendationsList(HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		
		try
		{
			HttpSession session=request.getSession(true);
			String userid=String.valueOf(session.getAttribute("user"));
			
			Recommendations obj=new Recommendations();
			obj.setUserid(userid);
			obj.getReport();
			
			ArrayList<Recommendations> lst1=new ArrayList<Recommendations>();
			ArrayList<Recommendations> lst2=new ArrayList<Recommendations>();
			ArrayList<Recommendations> lst3=new ArrayList<Recommendations>();
			
			lst1=obj.getLst1();
			lst2=obj.getLst2();
			lst3=obj.getLst3();
			
			mv.addObject("prefreport1",lst1);
			mv.addObject("prefreport2",lst2);
			mv.addObject("prefreport3",lst3);
			mv.setViewName("Recommendations.jsp");
		
		}
		catch(Exception ex) {}
		
		return mv;			
	}
	
	@RequestMapping("profilewise")
	public ModelAndView proflewiseShow(HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		try
		{
			HttpSession session=request.getSession(true);
			String userid=String.valueOf(session.getAttribute("user"));
			
			ProfilewiseRecommendation obj=new ProfilewiseRecommendation();
			obj.setUserid(userid);
			
			ArrayList<ProfilewiseRecommendation> lst=new ArrayList<ProfilewiseRecommendation>();
			lst=obj.getGenderwiseRecommendation();
			
			mv.addObject("recreport",lst);
			
		}
		catch(Exception ex) {}
		mv.setViewName("ProfilewiseRecommendationReport.jsp");
		return mv;
	}
	
	
	@RequestMapping("profilereport")
	public ModelAndView proflewiseList(HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		
		try
		{
			HttpSession session=request.getSession(true);
			String userid=String.valueOf(session.getAttribute("user"));
			
			ProfilewiseRecommendation obj=new ProfilewiseRecommendation();
			obj.setUserid(userid);
			
			ArrayList<ProfilewiseRecommendation> lst=new ArrayList<ProfilewiseRecommendation>();
			String type=request.getParameter("type");
			
			if(type.equals("age"))			
				lst=obj.getAgewiseRecommendation();
			else if(type.equals("gender"))			
				lst=obj.getGenderwiseRecommendation();
			
			mv.addObject("recreport",lst);
			mv.setViewName("ShowRecommendationReport.jsp");
		
		}
		catch(Exception ex) {}
		
		return mv;			
	}
	
	@RequestMapping("topproducts")
	public ModelAndView topSoldProductsList()
	{
		ModelAndView mv=new ModelAndView();
		
		try
		{
			TopSoldProducts obj=new TopSoldProducts();
			
			ArrayList<TopSoldProducts> lst=new ArrayList<TopSoldProducts>();
			lst=obj.getReport();
			
			mv.addObject("prodreport",lst);
			mv.setViewName("TopProductsReport.jsp");
		
		}
		catch(Exception ex) {}
		
		return mv;			
	}
	
	
	@RequestMapping("topcategories")
	public ModelAndView topCategoriesList()
	{
		ModelAndView mv=new ModelAndView();
		
		try
		{
			TopCategories obj=new TopCategories();
			
			ArrayList<TopCategories> lst=new ArrayList<TopCategories>();
			lst=obj.getReport();
			
			mv.addObject("catreport",lst);
			mv.setViewName("TopCategoriesReport.jsp");
		
		}
		catch(Exception ex) {}
		
		return mv;			
	}
	
	
	@RequestMapping("topbrands")
	public ModelAndView topBrandsList()
	{
		ModelAndView mv=new ModelAndView();
		
		try
		{
			TopBrands obj=new TopBrands();
			
			ArrayList<TopBrands> lst=new ArrayList<TopBrands>();
			lst=obj.getReport();
			
			mv.addObject("brandreport",lst);
			mv.setViewName("TopBrandsReport.jsp");
		
		}
		catch(Exception ex) {}
		
		return mv;			
	}
	
}
