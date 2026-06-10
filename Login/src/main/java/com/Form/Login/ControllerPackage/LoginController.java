package com.Form.Login.ControllerPackage;

import com.Form.Login.*;
import com.Form.Login.ServicePackage.LoginService;
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
    public ResponseEntity<UserResponseDto> userProfile(@PathVariable String id){
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

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id,
                                             @RequestBody UserUpdateDto dto){

        String returnedValue= loginService.updateUser(id,dto);

        if(returnedValue.equalsIgnoreCase("Successfully updated")){
            return ResponseEntity.ok("Updated successfully");

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("not updated");

    }


    @DeleteMapping("/removeEmployee/{id}")
    public ResponseEntity<String> removeEmployee(@PathVariable String id){
        String returnedValue=loginService.removeEmployee(id);
        if(returnedValue.equals("deleted")) {
            return ResponseEntity.ok("Delete successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("not deleted");
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<String> addEmployee(@RequestBody User user){
        String response= loginService.addEmployee(user);
        if(response.equals("success")){
            return ResponseEntity.ok("Created Successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("User not created");
    }


    @PostMapping("/login/forgotPassword/{id}")
    public ResponseEntity<String> resetPassword(@PathVariable String id,
                                                @RequestBody String name){
        String response= loginService.resetPassword(id, name);

        if(response.equals("invalid")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(response);
        }

        return ResponseEntity.ok(response);
    }






}
