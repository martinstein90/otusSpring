package domain;

import java.util.Map;
import java.util.Set;

public class Question {
    private final String question;
    private final Map<String, Integer> answers;

    public Question(String question, Map<String, Integer> answers) {
        System.out.println("Question(String question, Map<String, Integer> answers) question" + question);
        this.question = question;
        this.answers = answers;
    }

    public String getQustion() {
        return question;
    }

    public String[] getAnswers() {
        Set<String> setAnswers  = answers.keySet();
        return setAnswers.toArray(new String[setAnswers.size()]);
    }

    public Integer getScore(String answer) {
        return answers.get(answer);
    }
}
