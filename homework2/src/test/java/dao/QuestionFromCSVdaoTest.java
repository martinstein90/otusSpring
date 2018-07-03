package dao;

import domain.Question;
import domain.Result;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class QuestionFromCSVdaoTest {

    @Test(expected = RuntimeException.class)
    public void throwsRuntimeExceptionByParseInt() {
        InterviewFromCSVdao q = new InterviewFromCSVdao("src\\test\\resources\\Can you stand up for the truth BAD PARSE INT.csv");
    }

    @Test(expected = RuntimeException.class)
    public void throwsRuntimeExceptionByLoadQuestion() {
        InterviewFromCSVdao q = new InterviewFromCSVdao("src\\test\\resources\\Can you stand up for the truth BAD QUESTION.csv");
    }

    @Test(expected = RuntimeException.class)
    public void throwsRuntimeExceptionByLoadResult() {
        InterviewFromCSVdao q = new InterviewFromCSVdao("src\\test\\resources\\Can you stand up for the truth BAD RESULT.csv");
    }

    @Test
    public void findQuestionByNumberAndCheckAnswers() {
        InterviewFromCSVdao q = new InterviewFromCSVdao("src\\test\\resources\\Can you stand up for the truth GOOD.csv");
        Question question = q.findQuestionByNumber(2);
        Assert.assertEquals(question.getQustion(), "Вопрос2");
        String[] answers = question.getAnswers();
        Set<String> setAnswers = new HashSet<>(Arrays.asList(answers));
        Assert.assertEquals(setAnswers.contains("Ответ21"), true);
        Assert.assertEquals(setAnswers.contains("Ответ22"), true);
        Assert.assertEquals(setAnswers.contains("Ответ23"), true);
        Assert.assertEquals(setAnswers.contains("Ответ11"), false);

    }

    @Test
    public void getResultByScoreCheck() {
        InterviewFromCSVdao q = new InterviewFromCSVdao("src\\test\\resources\\Can you stand up for the truth GOOD.csv");
        Result result = q.getResultByScore(10);
        Assert.assertEquals(result.getResult(), "Результат1");

    }

    @Test(expected = RuntimeException.class)
    public void throwsRuntimeExceptionByFileNotFound() {
        InterviewFromCSVdao q = new InterviewFromCSVdao("src\\test\\resources\\file not found.csv");
    }
}