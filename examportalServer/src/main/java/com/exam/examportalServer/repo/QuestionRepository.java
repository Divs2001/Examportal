package com.exam.examportalServer.repo;

import com.exam.examportalServer.entity.Question;
import com.exam.examportalServer.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Modifying
    @Query(value = "DELETE FROM question WHERE ques_id = :quesId", nativeQuery = true)
    public void deleteQuestion(Long quesId);
}
