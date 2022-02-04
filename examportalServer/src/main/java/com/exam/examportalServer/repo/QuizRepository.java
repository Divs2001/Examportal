package com.exam.examportalServer.repo;

import com.exam.examportalServer.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM quiz WHERE quiz_id = :quizId", nativeQuery = true)
    public void deleteQuiz(Long quizId);
}
