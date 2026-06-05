package com.Form.Login.ControllerPackage;

import com.Form.Login.ServicePackage.LoginService;
import com.Form.Login.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@CrossOrigin(origins = "*")
public class LoginController {

    public LoginController(LoginService loginService){
        this.loginService= loginService;

    }
    private final LoginService loginService;


    @PostMapping("/signup")
    public String addUser(@RequestBody User user){
       return loginService.addUser(user);

    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user){
        String returnValue= loginService.loginUser(user);
        if(returnValue.equalsIgnoreCase("successfully login")) {
            return ResponseEntity.ok("Login successfully");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid access");
    }


    @GetMapping("/profile/{id}")
    public ResponseEntity<User> userProfile(@PathVariable String id){
         return loginService.userProfile(id);
    }

    @GetMapping("/homepage")
    public String homePage(){
        return "Welcome to the Dashboard";
    }



}
