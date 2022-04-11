package com.exam.examportalServer.repo;

import com.exam.examportalServer.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @Modifying
    @Query(value = "DELETE FROM quiz WHERE quiz_id = :quizId", nativeQuery = true)
    public void deleteQuiz(Long quizId);

    @Query(value="select * from quiz q left join category c on q.category_cat_id=c.cat_id where c.cat_id= :catId", nativeQuery = true)
    Set<Quiz> findQuizzesByCat(Long catId);

    @Query(value="select * from quiz where active=1", nativeQuery = true)
    public Set<Quiz> findQuizzesByActive();

    @Query(value="select * from quiz q left join category c on q.category_cat_id=c.cat_id where c.cat_id= :catId and q.active=1", nativeQuery = true)
    Set<Quiz> findQuizzesByCatAndActive(Long catId);
}
