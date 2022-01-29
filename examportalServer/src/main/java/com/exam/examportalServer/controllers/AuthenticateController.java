package com.exam.examportalServer.controllers;

import com.exam.examportalServer.config.JwtUtil;
import com.exam.examportalServer.entity.JwtRequest;
import com.exam.examportalServer.entity.JwtResponse;
import com.exam.examportalServer.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try{
            authenticate(jwtRequest.getUserName(), jwtRequest.getPassword());
        }catch(UsernameNotFoundException e){
            e.printStackTrace();
            throw new Exception("User not found");
        }

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUserName());
        String token = this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String userName, String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

        }catch(DisabledException e){
            throw new Exception("User Disabled");
        }catch(BadCredentialsException e){
            throw new Exception("Invalid Credentials"+ e.getMessage());
        }
    }
}
