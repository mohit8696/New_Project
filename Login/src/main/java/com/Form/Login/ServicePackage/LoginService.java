package com.Form.Login.ServicePackage;

import com.Form.Login.*;
import com.Form.Login.RepositoryPackage.LoginRepository;
import org.springframework.data.jpa.repository.Query;
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


       if(ogUser!=null && ogUser.getUserType().equals("user")){
           String pass =user.getPassword();
           if(ogUser.getPassword().equals(pass)){
               return new LoginResponse(user.getUserId(),"user","user login");
           }

       }
       else if (ogUser!=null && ogUser.getUserType().equals("admin")) {
           String pass= user.getPassword();
           if (ogUser.getPassword().equals(pass)){
               return new LoginResponse(user.getUserId(),"admin","admin login");
           }

       }
       return new LoginResponse(null, "null","unathurized login");
    }


    public ResponseEntity<UserResponseDto> userProfile(String id) {

            User user= loginRepository.findById(id).orElse(null);
            if(user!=null) {
                UserResponseDto userResponse = new UserResponseDto();
                userResponse.setUserId(user.getUserId());
                userResponse.setEmail(user.getEmail());
                userResponse.setName(user.getName());
                userResponse.setUserType(user.getUserType());
                return ResponseEntity.ok(userResponse);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(null);
    }


    public List<User> getAllUser() {
       return loginRepository.findAll();
    }

    public String updateUser(String id,UserUpdateDto dto) {
        User savedUser= loginRepository.findById(id).orElse(null);
        if(savedUser.getUserType().equals("admin")){
            return "You don't have as much authority";
        }
        savedUser.setName(dto.getName());
        savedUser.setEmail(dto.getEmail());
        savedUser.setUserType(dto.getUserType());
        loginRepository.save(savedUser);
        return "Successfully updated";
    }

    public String  removeEmployee(String id) {
        User user= loginRepository.findById(id).orElse(null);
        String userType= user.getUserType();
        if(userType.equals("admin")){
            return "You can't delete admin";
        }
        loginRepository.deleteById(id);
        return "deleted";
    }

    public String addEmployee(User user) {
        String newUserId= user.getUserId();
        List<User> list= loginRepository.findAll();
        for(int i=0;i<list.size();i++){
           if( list.get(i).getUserId()==newUserId){
               return "user with this id already exist";
           }
        }
        loginRepository.save(user);
        return "success";
    }

    public String resetPassword(String id, String email) {
        User user= loginRepository.findById(id).orElse(null);

        if(user!=null){
            if(user.getEmail().equalsIgnoreCase(email)){
                String password=user.getPassword();

                return password;
            }
        }

        return "invalid";

    }
    public List<User> searchByKeyword(String keyword){
        List<User> users=loginRepository.findByKeyword(keyword);

        return users;

    }
}
