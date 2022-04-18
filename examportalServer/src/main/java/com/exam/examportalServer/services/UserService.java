package com.exam.examportalServer.services;

import com.exam.examportalServer.entity.*;
import com.exam.examportalServer.repo.PasswordChangeRepository;
import com.exam.examportalServer.repo.RoleRepository;
import com.exam.examportalServer.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordChangeRepository passwordChangeRepository;

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

    public ForgotPasswordResponse forgotPassword(String email) {
        User user = this.userRepository.findByEmail(email);
        if(user!=null){
            Properties properties = new Properties();

            properties.put("mail.smtp.auth","true");
            properties.put("mail.smtp.starttls.enable","true");
            properties.put("mail.smtp.host","smtp.gmail.com");
            properties.put("mail.smtp.port","587");

            String to = email;//change accordingly
            String from = "onlinehelpdeskskit@gmail.com";//change accordingly
            String password = "Onlinehelpdesk";

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });

            //entry in password change table
            PasswordChange passwordChange = new PasswordChange();
            passwordChange.setEmail(user.getEmail());
            passwordChange.setTime(LocalDateTime.now());
            this.passwordChangeRepository.save(passwordChange);


            //compose the message
            try{
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
                message.setSubject("Reset password for Online Help Desk");
                message.setText("Please set your password: http://localhost:4200/reset-password/"+passwordChange.getId());

                // Send message
                Transport.send(message);
                System.out.println("message sent successfully....");

            }catch (MessagingException mex) {mex.printStackTrace();}
            return new ForgotPasswordResponse("Email has been sent...");
        }
        return new ForgotPasswordResponse("Enter valid email or signup.");
    }


    public ResetPasswordResponse resetPassword(String id, String new_password) {
        System.out.println(id);
        PasswordChange pc = this.passwordChangeRepository.findByRprId(id);

        System.out.println(pc);
        System.out.println(pc.getEmail());
        User user = this.userRepository.findByEmail(pc.getEmail());
        System.out.println(user.getPassword());
        System.out.println(new_password);
        user.setPassword(bCryptPasswordEncoder.encode(new_password));
        System.out.println(user.getPassword());
        this.userRepository.save(user);

        return new ResetPasswordResponse("Password is successfully changed.");
    }
}
