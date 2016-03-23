package com.bignerdranch.meha.geoquiz;

/**
 * Created by meha on 2/26/16.
 */
public class QuestionModel {
    int question;
    boolean answer;

    public QuestionModel(int question, boolean answer) {
        this.question = question;
        this.answer = answer;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
