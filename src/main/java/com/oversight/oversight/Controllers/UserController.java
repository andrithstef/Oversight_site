package com.oversight.oversight.Controllers;

import com.oversight.oversight.Persistence.Entities.User;
import com.oversight.oversight.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/")
    public String homePage(Model model){
        return "home";
    }

    //Redirect us to user creation page
    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public String createuserGET(User user){
        //fara yfir á síðuna createUser
        return "createUser";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String createuserPOST(User user, BindingResult result, Model model){
        if (result.hasErrors()){
            return "createUser";
        }
        User exists = userService.findByUsername(user.getUsername());
        if (exists == null) {
            userService.save(user);
        }
        return "redirect:/seeTransactions";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginUserGET(User user){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUserPOST(User user, BindingResult result, Model model, HttpSession session){
        if (result.hasErrors()){
            return "home";
        }
        User exists = userService.findByUsername(user.getUsername());
        if (exists != null){
            session.setAttribute("LoggedInUser", exists);
            model.addAttribute("LoggedInUser", exists);
        }
        return "home";
    }
}
