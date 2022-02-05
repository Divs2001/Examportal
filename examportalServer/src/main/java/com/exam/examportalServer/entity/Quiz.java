package com.exam.examportalServer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class  Quiz
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long quizId;
    private String title;
    private String description;
    private int maxMarks;
    private int numberOfQuestions;
    private boolean active = false;

    @ManyToOne(fetch=FetchType.EAGER)
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "quiz")
    @JsonIgnore
    private Set<Question> questions = new HashSet<>();

    public Quiz(String title, String description, int maxMarks, int numberOfQuestions, boolean active) {
        this.title = title;
        this.description = description;
        this.maxMarks = maxMarks;
        this.numberOfQuestions = numberOfQuestions;
        this.active = active;
    }
}
