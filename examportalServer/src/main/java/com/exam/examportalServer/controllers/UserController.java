package com.exam.examportalServer.controllers;

import com.exam.examportalServer.entity.User;
import com.exam.examportalServer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping(path="/getUser/{userName}")
//    public User getUser(@RequestParam Long id){
    public User getUser(@PathVariable String userName){
        return userService.getUser(userName);
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

}
