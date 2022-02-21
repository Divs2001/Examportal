package com.exam.examportalServer.controllers;

import com.exam.examportalServer.entity.Question;
import com.exam.examportalServer.entity.Quiz;
import com.exam.examportalServer.services.impl.QuestionServiceImpl;
import com.exam.examportalServer.services.interfaces.QuestionService;
import com.exam.examportalServer.services.interfaces.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionServiceImpl questionServiceImpl;

    @PostMapping("/addQuestion")
    public Question addQuestion(@RequestBody Question question){
        return this.questionServiceImpl.addQuestion(question);
    }

    @PutMapping("/updateQuestion")
    public Question updateQuestion(Question question){
        return this.questionServiceImpl.updateQuestion(question);
    }

    @GetMapping("/getQuestions")
    public Set<Question> getQuestions(){
        return this.questionServiceImpl.getQuestions();
    }

    @GetMapping("/getQuestion")
    public Question getQuestion(@RequestParam Long quesId){
        return this.questionServiceImpl.getQuestion(quesId);
    }

    @GetMapping("/getQuestionOfQuiz")
    public ResponseEntity<?> getQuestionOfQuiz(@RequestParam Long quizId){
        return ResponseEntity.ok(this.questionServiceImpl.getQuestionOfQuiz(quizId));
    }

    @GetMapping("/getAllQuestionOfQuiz")
    public ResponseEntity<?> getQuestionOfQuizAdmin(@RequestParam Long quizId) {
        return this.questionServiceImpl.getQuestionOfQuizAdmin(quizId);
    }

    @DeleteMapping("/deleteQuestion")
    public void deleteQuestion(@RequestParam Long quesId){
        this.questionServiceImpl.deleteQuestion(quesId);
    }
}
