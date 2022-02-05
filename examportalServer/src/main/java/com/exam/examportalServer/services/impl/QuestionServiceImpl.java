package com.exam.examportalServer.services.impl;

import com.exam.examportalServer.entity.Question;
import com.exam.examportalServer.entity.Quiz;
import com.exam.examportalServer.repo.QuestionRepository;
import com.exam.examportalServer.services.interfaces.QuestionService;
import com.exam.examportalServer.services.interfaces.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizService quizService;

    @Override
    public Question addQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Set<Question> getQuestions() {
        return new HashSet<>(this.questionRepository.findAll());
    }

    @Override
    public Question getQuestion(Long quesId) {
        return this.questionRepository.findById(quesId).orElse(null);
    }

    @Override
    public ResponseEntity<?> getQuestionOfQuiz(Long quizId) {
        Quiz quiz = this.quizService.getQuiz(quizId);
        Set<Question> questions = quiz.getQuestions();

        List<Question> list = new ArrayList<>(questions);
        if(list.size()> quiz.getNumberOfQuestions()){
            list = list.subList(0, quiz.getNumberOfQuestions()+1);
        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);

    }

    @Override
    @Transactional
    public void deleteQuestion(Long quesId) {
//        Question q = new Question();
//        q.setQuesId(quesId);
//
//        this.questionRepository.delete(q);
//        this.questionRepository.delete(q);
        this.questionRepository.deleteQuestion(quesId);
//        this.questionRepository.deleteById(quesId);
    }

}
