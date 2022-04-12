package com.oversight.oversight.Controllers;

import com.oversight.oversight.Persistence.Entities.AppUser;
import com.oversight.oversight.Persistence.Entities.User;
import com.oversight.oversight.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Base64;

@RestController
public class UserRestController {
    private UserService userService;
    private static final Base64.Decoder base64Decoder = Base64.getDecoder(); // for decoding server calls

    @Autowired
    public UserRestController(UserService userService){
        this.userService = userService;
    }


    @RequestMapping("/loginUser/{userToken}")
    public AppUser loginApp(@PathVariable(value = "userToken") String userToken){
        System.out.println("Encoded string is: " + userToken);
        Pair<String, String> decodedEmailAndPassword = myDecoder(userToken);
        String userName = decodedEmailAndPassword.getFirst();
        String password = decodedEmailAndPassword.getSecond();

        User exists = userService.findByUsername(userName);
        return exists.getAppUser();
    }

    //create user
    @RequestMapping("/a")
    public AppUser a(){
        User a = new User("Hallo", "heimur");
        return a.getAppUser();
    }



    private Pair<String, String> myDecoder(String userToken) {
        String decodedToken = new String(base64Decoder.decode(userToken));
        System.out.println("Decoded string is: " + decodedToken);
        int i;
        for (i = 0; i < decodedToken.length(); i++) {
            if (decodedToken.charAt(i) == '%') {
                break;
            }
        }
        // We now have the index of the end of the email and the start of the password
        String email = decodedToken.substring(0,i);
        String password = decodedToken.substring(i+1);
        System.out.println("here is the decoded email: " + email);
        System.out.println("here is the decoded password: " + password);
        return Pair.of(email, password);
    }

}
