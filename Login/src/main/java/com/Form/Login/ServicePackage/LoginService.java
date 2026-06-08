package com.Form.Login.ServicePackage;

import com.Form.Login.Admin;
import com.Form.Login.LoginResponse;
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

    public LoginResponse loginUser(User user) {
       String id= user.getUserId();
       User ogUser=loginRepository.findById(id).orElse(null);

       if(ogUser!=null){
           String pass =user.getPassword();
           if(ogUser.getPassword().equals(pass)){
               return new LoginResponse(user.getUserId(),"successfully Login");
           }

       }
       return new LoginResponse(null,"User not found");
    }


    public ResponseEntity<User> userProfile(String id) {

            User user= loginRepository.findById(id).orElse(null);
            if(user!=null) {
                return ResponseEntity.ok(user);
            }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(null);


    }

    public String adminLogin(Admin admin) {
        String adminId= admin.getAdminId();
        String password= admin.getPassword();

        if(adminId.equals("admin") && password.equals("admin")){
            return "login successfull";
        }

        return "Unauthorized access";

    }

    public List<User> getAllUser() {
       return loginRepository.findAll();
    }

    public String updateUser(User user) {
        loginRepository.save(user);
        return "Successfully updated";
    }

    public String  removeEmployee(String id) {
        loginRepository.deleteById(id);
        return "deleted";
    }
}
