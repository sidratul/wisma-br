
package com.p4.wismabr.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    
    @RequestMapping("/login/")
    public String login(ModelMap mm){        
        return "login/login";
    }
    
    @RequestMapping("/login/loginfailed")
    public String  gagalLogin(ModelMap mm){        
        mm.addAttribute("error", true);
        return "login/login";
    }
    
    @RequestMapping("/login/logout")
    public String logout(ModelMap mm){                
        return "login/login";
    }
}
