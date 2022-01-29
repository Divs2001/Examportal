package com.exam.examportalServer.services;

import com.exam.examportalServer.entity.User;
import com.exam.examportalServer.repo.UserRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepositoy userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepository.findByUserName(username);
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Not a valid User");
        }

        return user;
    }
}
