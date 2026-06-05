package com.Form.Login.ServicePackage;

import com.Form.Login.RepositoryPackage.LoginRepository;
import com.Form.Login.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    public LoginService(LoginRepository loginRepository){
        this.loginRepository=loginRepository;
    }

    private final LoginRepository loginRepository;
    public String addUser(User user) {
        List<User> userList= loginRepository.findAll();
        for(int i=0; i<userList.size();i++){
            if(user.getUserId().equals(userList.get(i).getUserId())){
                return "Already have an account";
            }
        }
        loginRepository.save(user);
        return "Successfully login";



    }

    public String loginUser(User user) {
       String id= user.getUserId();
       User ogUser=loginRepository.findById(id).orElse(null);

       if(ogUser!=null){
           String pass = user.getPassword();
           if(ogUser.getPassword().equals(pass)){
               return "successfully Login";
           }

       }
       return "User not found";
    }


    public ResponseEntity<User> userProfile(String id) {

            User user= loginRepository.findById(id).orElse(null);
            if(user!=null) {
                return ResponseEntity.ok(user);
            }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(null);


    }
}
