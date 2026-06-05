package com.Form.Login.ControllerPackage;

import com.Form.Login.Admin;
import com.Form.Login.LoginResponse;
import com.Form.Login.ServicePackage.LoginService;
import com.Form.Login.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<LoginResponse> loginUser(@RequestBody User user){

        LoginResponse response= loginService.loginUser(user);
        if(response.getMessage().equalsIgnoreCase("successfully login")) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }


    @GetMapping("/profile/{id}")
    public ResponseEntity<User> userProfile(@PathVariable String id){
         return loginService.userProfile(id);
    }

    @GetMapping("/homepage")
    public String homePage(){
        return "Welcome to the Dashboard";
    }

    @PostMapping("/admin/login")
    public ResponseEntity<String> adminLogin(@RequestBody Admin admin){
        String status=loginService.adminLogin(admin);
        if(status.equals("login successfull")){
            return ResponseEntity.ok("Admin login successfully");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Unauthorized access");

    }


    @GetMapping("/employees")
    public List<User> getAllUser(){
        return loginService.getAllUser();
    }



}
