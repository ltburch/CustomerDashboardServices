package com.blackhills.customerdashboard;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserService {
    @RequestMapping("/getuser")
    public User getUser(@RequestParam(value="userid", defaultValue="World") String name) {
    	User u = new User();
    	u.id = name;
    	u.fullName = "Lee Burch";
    	
        return u;
    }

}
