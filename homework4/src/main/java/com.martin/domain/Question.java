package com.martin.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Question {
    private final String question;
    private final Map<String, Integer> answers;

    public Question(String question, Map<String, Integer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        Set<String> setAnswers  = answers.keySet();
        return new ArrayList<>(setAnswers);
    }

    public Integer getScore(String answer) {
        return answers.get(answer);
    }
}
