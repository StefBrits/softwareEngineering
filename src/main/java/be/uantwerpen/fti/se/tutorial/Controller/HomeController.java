package be.uantwerpen.fti.se.tutorial.Controller;

import be.uantwerpen.fti.se.tutorial.Model.User;
import be.uantwerpen.fti.se.tutorial.Model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @ModelAttribute("allUsers")
    public Iterable<User> populateUsers() {
        return this.userService.findAll();
    }
    @RequestMapping({"/","/home"})
    public String showHomepage(){
        return "homepage";
    }
}
