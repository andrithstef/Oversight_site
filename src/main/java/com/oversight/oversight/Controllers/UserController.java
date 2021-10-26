package com.oversight.oversight.Controllers;

import com.oversight.oversight.Persistence.Entities.User;
import com.oversight.oversight.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/")
    public String homePage(Model model, HttpSession session){
        return "home";
    }

    //Redirect us to user creation page
    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public String createuserGET(User user){
        //fara yfir á síðuna createUser
        return "createUser";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String createuserPOST(User user, BindingResult result, Model model, HttpSession session){
        if (result.hasErrors()){
            return "createUser";
        }
        User exists = userService.findByUsername(user.getUsername());
        if (exists == null) {
            //save user and log in
            exists = userService.save(user);
            session.setAttribute("LoggedInUser", exists);
            model.addAttribute("LoggedInUser", exists);
            return "loggedIn";
        }
        return "createUser";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginUserGET(User user){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUserPOST(User user, BindingResult result, Model model, HttpSession session){
        if (result.hasErrors()){
            return "loggedIn";
        }
        User exists = userService.findByUsername(user.getUsername());
        if (exists != null && exists.getPassword().equals(user.getPassword())){
            session.setAttribute("LoggedInUser", exists);
            model.addAttribute("LoggedInUser", exists);
            return "loggedIn";
        }
        return "home";
    }

    //Log out of your account
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        session.setAttribute("LoggedInUser", null);
        return "home";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String deleteUserGET(User user){
        return "deleteUser";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public String deleteUserPOST(User tempUser, HttpSession session){
        User loggedIn = (User) session.getAttribute("LoggedInUser");
        loggedIn = userService.findByID(loggedIn.getID());
        String pass = tempUser.getPassword();
        if (loggedIn.getPassword().equals(pass)){
            session.setAttribute("LoggedInUser", null);
            userService.delete(loggedIn);
            return "home";
        }
        return "/deleteUser";
    }

    //public String changePassword(User user, String password)
}
