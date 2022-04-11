package com.exam.examportalServer.controllers;

import com.exam.examportalServer.entity.Quiz;
import com.exam.examportalServer.services.impl.QuizServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

    @Autowired
    private QuizServiceImpl quizServiceImpl;

    @PostMapping("/addQuiz")
    public Quiz addQuiz(@RequestBody Quiz quiz){
        return this.quizServiceImpl.addQuiz(quiz);
    }

    @PutMapping("/updateQuiz")
    public Quiz updateQuiz(@RequestBody Quiz quiz){
        return this.quizServiceImpl.updateQuiz(quiz);
    }

    @GetMapping("/getQuizzes")
    public ResponseEntity<Set<Quiz>> getQuizzes(){
        return ResponseEntity.ok(this.quizServiceImpl.getQuizzes());
    }

    @GetMapping("/getQuiz")
    public Quiz getQuiz(@RequestParam Long quizId){
        return this.quizServiceImpl.getQuiz(quizId);
    }

    @GetMapping("/getQuizzesForACategory")
    public Set<Quiz> getQuizzesForACategory(@RequestParam Long catId){
        return this.quizServiceImpl.getQuizzesForACategory(catId);
    }

    @GetMapping("getQuizzesByActive")
    public Set<Quiz> getQuizzesByActive(){
        return this.quizServiceImpl.getQuizzesByActive();
    }

    @GetMapping("/getQuizzesForACategoryAndActive")
    public Set<Quiz> getQuizzesForACategoryAndActive(@RequestParam Long catId){
        return this.quizServiceImpl.getQuizzesForACategoryAndActive(catId);
    }

    @DeleteMapping("/deleteQuiz")
    public void deleteQuiz(@RequestParam Long quizId){
        this.quizServiceImpl.deleteQuiz(quizId);
    }

}
