package com.practices.quizservlet_practices.model;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Question {
    private String questionText;
    private List<String> options;
    private int correctAnswerIndex;

    public boolean isAnswerCorrect(int selectedIndex){
        return selectedIndex == correctAnswerIndex;
    }
}
