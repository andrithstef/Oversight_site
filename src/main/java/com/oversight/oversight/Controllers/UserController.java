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
import java.security.MessageDigest;

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
        //Hash the password and replace the unhashed one
        String hashedPassword = userService.get_SHA_512(user.getPassword());
        user.setPassword(hashedPassword);
        User exists = userService.findByUsername(user.getUsername());

        if (exists == null) {
            //the user does not exist
            //save user and log in
            exists = userService.save(user);
            session.setAttribute("LoggedInUser", exists);
            model.addAttribute("LoggedInUser", exists);
            return "loggedIn";
        }
        //Try again
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
        //Hash the inserted password to make sure it's correct
        String hashedPassword = userService.get_SHA_512(user.getPassword());
        User exists = userService.findByUsername(user.getUsername());

        //The user exists and the password is correct
        //log in and move to user page
        if (exists != null && hashedPassword.equals(exists.getPassword())){
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
        //Get logged in user
        User loggedIn = (User) session.getAttribute("LoggedInUser");
        loggedIn = userService.findByID(loggedIn.getID());

        //Hash the inserted password to check against the real password
        String pass = tempUser.getPassword();
        String hashedPassword = userService.get_SHA_512(pass);

        if (loggedIn.getPassword().equals(hashedPassword)){
            //The password is correct
            session.setAttribute("LoggedInUser", null);
            userService.delete(loggedIn);
            return "home";
        }
        //Try again
        return "/deleteUser";
    }

    //public String changePassword(User user, String password)
}
