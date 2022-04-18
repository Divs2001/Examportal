package com.exam.examportalServer.repo;

import com.exam.examportalServer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);

    User findByEmail(String email);

//    @Query("delete u from user_role where u.user_id=:id")
//    public void deleteUserFromUser_Role(@Param("id") Long id);

}
