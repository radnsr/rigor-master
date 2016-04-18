package com.rigor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class IndexController {

	@RequestMapping("/dashboard")
	public ModelAndView dashboard(){
		
		return new ModelAndView("dashboard");
	}
	
	
	@RequestMapping("/user")
	public ModelAndView User(){
		
		return new ModelAndView("user");
	}
	@RequestMapping("/master_dept")
	public ModelAndView MasterDept(){
		
		return new ModelAndView( "master_dept");
	}
	@RequestMapping("/error")
	public ModelAndView errorLogin(){
		
		return new ModelAndView("errorLogin");
	}
	@RequestMapping("/index")
	public ModelAndView Index(){
		
		return new ModelAndView("index");
	}

}
