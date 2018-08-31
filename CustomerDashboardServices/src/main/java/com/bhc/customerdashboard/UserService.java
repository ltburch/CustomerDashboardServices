package com.bhc.customerdashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bhc.customerdashboard.webservice.UserWebService;
import com.bhc.soa.web.types.messages.user.v1.BhcUserType;

@RestController
public class UserService {
	private UserWebService userWebService; 
	ApplicationContext ctx;
	
	
	@Autowired
	public void setUserWebService(UserWebService userWebService) {
		this.userWebService = userWebService;
	}


	@Bean
	ApplicationContext getApplicationContext() {
		return ctx;
	}
	
	
	UserService() {
//		// open/read the application context file
//	    ctx = new ClassPathXmlApplicationContext("LocalApplicationContext.xml");
//	    userWebService = (UserWebService)ctx.getBean("userService");
	}


	@RequestMapping("/getuser")
    public BhcUserType getUser(@RequestParam(value="userid", defaultValue="World") String name) {
    	try {
    		BhcUserType u = userWebService.getLoggedInUserDetails(name);
            return u;
    	} catch (Exception e) {
    		return null;
    	}    	
    }

}
