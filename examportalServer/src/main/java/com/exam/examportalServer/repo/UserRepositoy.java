package com.exam.examportalServer.repo;

import com.exam.examportalServer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoy extends JpaRepository<User, Long> {

    public User findByUserName(String userName);

//    @Query("delete u from user_role where u.user_id=:id")
//    public void deleteUserFromUser_Role(@Param("id") Long id);

}
