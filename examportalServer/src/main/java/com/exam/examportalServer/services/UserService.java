package com.exam.examportalServer.services;

import com.exam.examportalServer.entity.Role;
import com.exam.examportalServer.entity.User;
import com.exam.examportalServer.repo.RoleRepository;
import com.exam.examportalServer.repo.UserRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepositoy userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(String username) {
//        return userRepository.findById(id).orElse(null)
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) throws Exception {

        User newUser = userRepository.findByUsername(user.getUsername());
        if (newUser != null) {
//            System.out.println("User is already present");
//            return newUser;
            throw new Exception("User is already present!!");
        } else {
            newUser = new User(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(),
                    user.isEnabled(), user.getProfileImage());

            newUser.setProfileImage("default.png");
            //encoding password
            newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            Set<Role> roles = new HashSet<>();
            Role role;
            if (roleRepository.findByRoleName("NORMAL") == null) {
                role = new Role("NORMAL");
            } else {
                role = roleRepository.findByRoleName("NORMAL");
            }

            role.addUser(newUser);
            roles.add(role);

            newUser.setRoles(roles);
        }
        userRepository.save(newUser);
        return newUser;
    }


    public void deleteUser(Long id) {

        User user = userRepository.findById(id).orElse(null);

        //deleting the data from user_role
        user.getRoles().removeAll(user.getRoles());
        userRepository.deleteById(id);
    }
}
