package com.mendesk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mendesk.util.AuthSystem;

@Controller
public class LoginController {
	 @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public String loginPage(@RequestParam(value = "error", required = false) String error,
	                            @RequestParam(value = "logout", required = false) String logout,
	                            Model model) {
		 
		 	if (AuthSystem.isLogged()) {
		 		return "redirect:/dashboard";
			}
		 	
	        String errorMessage = null;
	        
	        if(error != null) {
	        	errorMessage = "Your credentials are not valid.";
	        }
	        
	        if(logout != null) {
	        	errorMessage = "You have been successfully logged out !!";
	        }
	        
	        model.addAttribute("errorMessage", errorMessage);
	        
	        return "login/login";
	    }
	  
	    @RequestMapping(value="/logout", method = RequestMethod.GET)
	    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        
	        if (auth != null){   
	            new SecurityContextLogoutHandler().logout(request, response, auth);
	        }
	        
	        return "redirect:/login?logout=true";
	    }
}
