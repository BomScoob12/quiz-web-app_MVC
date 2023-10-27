package com.practices.quizservlet_practices.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class QuizSession {
    private List<Question> questions;
    private int currentQuestionIndex;
    private int correctAnswerCount;

    public QuizSession(List<Question> list){
        this.questions = list;
    }

    public Question getCurrentQuestion(){
        return questions.get(currentQuestionIndex);
    }

    public void moveToNextQuestions(){
        currentQuestionIndex++;
    }

    public void resetQuiz(){
        currentQuestionIndex = 0;
        correctAnswerCount = 0;
    }

    public void checkAnswer(int selectedIndex){
        if(questions.get(currentQuestionIndex).isAnswerCorrect(selectedIndex)) {
            correctAnswerCount++;
        }
    }

    public boolean isQuizOver(){
        return currentQuestionIndex >= questions.size();
    }

}
