package com.exam.examportalServer.services.impl;

import com.exam.examportalServer.entity.Quiz;
import com.exam.examportalServer.repo.QuizRepository;
import com.exam.examportalServer.services.interfaces.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Set<Quiz> getQuizzes() {
        return new LinkedHashSet<>(this.quizRepository.findAll());
    }

    @Override
    public Quiz getQuiz(Long quizId) {
        return this.quizRepository.findById(quizId).orElse(null);
    }

    @Override
    @Transactional
    public void deleteQuiz(Long quizId) {
//        this.quizRepository.deleteById(quizId);
        this.quizRepository.deleteQuiz(quizId);
    }

    public Set<Quiz> getQuizzesForACategory(Long catId) {
        return this.quizRepository.findQuizzesByCat(catId);
    }

    public Set<Quiz> getQuizzesByActive() {
        return this.quizRepository.findQuizzesByActive();
    }

    public Set<Quiz> getQuizzesForACategoryAndActive(Long catId) {
        return this.quizRepository.findQuizzesByCatAndActive(catId);
    }
}
