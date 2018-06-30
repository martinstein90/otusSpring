package domain;

import java.util.Map;
import java.util.Set;

public class Question {
    private String question;
    private Map<String, Integer> answers;

    public Question(String question, Map<String, Integer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQustion() {
        return question;
    }

    public Set<String> getAnswers() {
        return answers.keySet();
    }

    public Integer getScore(String answer) {
        return answers.get(answer);
    }
}
