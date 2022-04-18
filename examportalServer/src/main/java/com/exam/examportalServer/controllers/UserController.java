package com.exam.examportalServer.controllers;

import com.exam.examportalServer.entity.*;
import com.exam.examportalServer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    //getting all users api
    @GetMapping(path="/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    //getting a user api
    @GetMapping(path="/getUser/{username}")
//    public User getUser(@RequestParam Long id){
    public User getUser(@PathVariable String username){
        return userService.getUser(username);
    }

    //saving a user api
    @PostMapping(path="/saveUser")
    public User saveUser(@RequestBody User user) throws Exception{
        return userService.saveUser(user);
    }

    //deleting a user api
    @DeleteMapping(path="/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id){

         userService.deleteUser(id);
//         if(getUser(id)==null){
//             return "User is deleted";
//         }else{
//             return "Error";
//         }
        return "Deleted";

    }

    @PostMapping(path="/forgot-password")
    public ForgotPasswordResponse forgotPassword(@RequestBody ForgotPasswordResource email){
        System.out.println("Entered in controller");
        return this.userService.forgotPassword(email.getEmail());
    }

    @PutMapping(path="/reset-password")
    public ResetPasswordResponse resetPassword(@RequestBody ResetPasswordResource rpr){
        System.out.println("I am in.");
        return this.userService.resetPassword(rpr.getId(),rpr.getNew_password());
    }

}
