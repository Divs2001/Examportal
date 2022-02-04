package com.exam.examportalServer.services.interfaces;

import com.exam.examportalServer.entity.Question;
import com.exam.examportalServer.entity.Quiz;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface QuestionService {

    public Question addQuestion(Question question);

    public Question updateQuestion(Question question);

    public Set<Question> getQuestions();

    public Question getQuestion(Long quesId);

    public ResponseEntity<?> getQuestionOfQuiz(Long quizId);

    public void deleteQuestion(Long quesId);
}
