package com.exam.examportalServer.repo;

import com.exam.examportalServer.entity.PasswordChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordChangeRepository extends JpaRepository<PasswordChange,String> {
    @Query(value="select * from password_change where id= :id", nativeQuery = true)
    PasswordChange findByRprId(String id);

//    PasswordChange findById(String id);

}
