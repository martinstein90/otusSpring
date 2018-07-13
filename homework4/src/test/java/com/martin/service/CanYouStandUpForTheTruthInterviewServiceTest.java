package com.martin.service;

import com.martin.domain.Result;
import com.martin.properties.ApplicationSettings;
import com.martin.reader.ConsoleReader;
import com.martin.dao.InterviewDao;
import com.martin.domain.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CanYouStandUpForTheTruthInterviewServiceTest {

    private final int MAX_AMOUNT_QUESTIONS = 500;

    @MockBean
    private InterviewDao dao;

    @MockBean
    private MessageSource messageSource;

    @MockBean
    private ConsoleReader consoleReader;

    @MockBean
    private ApplicationSettings settings;

    @Autowired
    private InterviewService canYouStandUpForTheTruthInterviewService;

    @Before
    public void setUp() throws IOException {
        initMockDao();
        initMockMessageSources();
        initMockConsoleReader();
        initApplicationSettings();
    }

    private void initMockDao() {
        initQuestions();
        initResults();
    }
    private void initQuestions() {
        List<Integer> questionsNumbers = new ArrayList<>();
        for(int i=1; i<=MAX_AMOUNT_QUESTIONS; i++)
            questionsNumbers.add(i);

        when(dao.getQuestionsNumbers()).thenReturn(questionsNumbers);

        for(int i=1 ; i<=MAX_AMOUNT_QUESTIONS; i++) {
            Map<String, Integer> answers = new HashMap<>();
            answers.put("Answer" + i + "_" + 1 , 1);
            answers.put("Answer" + i + "_" + 2 , 2);
            answers.put("Answer" + i + "_" + 3 , 3);
            when(dao.findQuestionByNumber(i)).thenReturn(new Question("Question" + i, answers));
        }

    }
    private void initResults(){
        List<Result> results = new ArrayList<>();
        for(int i=0, j=0; i<MAX_AMOUNT_QUESTIONS; i++, j+=10)
            results.add(new Result("Super result " + i +"!!!",  j, j+10));
        results.add(new Result("Super result MAX !!!",  100, Integer.MAX_VALUE));

        when(dao.getResultByScore(anyInt())).thenAnswer(invocation-> {
            Object[] args = invocation.getArguments();
            int i = (Integer)args[0];
            System.out.println("Score: " + i);
            return results.get(i/MAX_AMOUNT_QUESTIONS);
        });
    }

    private void initMockMessageSources() {
        when(messageSource.getMessage(eq("hello"),              eq(null), any())).thenReturn("Hello");
        when(messageSource.getMessage(eq("question"),           eq(null), any())).thenReturn("Question");
        when(messageSource.getMessage(eq("total"),              eq(null), any())).thenReturn("Total");
        when(messageSource.getMessage(eq("thank"),              eq(null), any())).thenReturn("Thank");
        when(messageSource.getMessage(eq("notNumber"),          eq(null), any())).thenReturn("Not number");
        when(messageSource.getMessage(eq("enterNumberRange"),   eq(null), any())).thenReturn("Enter Number Range");
    }

    private void initMockConsoleReader() throws IOException {
        List<String> list = Arrays.asList("1", "aa", "2", "-10", "3", "5", "-2", "a", "b", "c", "d", "1");
        Random rnd = new Random();
        when(consoleReader.readLine()).thenAnswer(invocation -> list.get(rnd.nextInt(list.size())));
    }

    private void initApplicationSettings() {
        when(settings.isEnglishLocal()).thenReturn(false);
    }

    @Test
    public void test1() {
        String userName = "Ivan";
        int amountQuestions = 200;
        if(amountQuestions < MAX_AMOUNT_QUESTIONS)
            canYouStandUpForTheTruthInterviewService.interview(userName, amountQuestions);
    }

    @Test
    public void test2() {
        String userName = "Kirill";
        int amountQuestions = 466;
        if(amountQuestions < MAX_AMOUNT_QUESTIONS)
            canYouStandUpForTheTruthInterviewService.interview(userName, amountQuestions);
    }

    @Test
    public void test3() {
        String userName = "Denis";
        int amountQuestions = 156;
        if(amountQuestions < MAX_AMOUNT_QUESTIONS)
            canYouStandUpForTheTruthInterviewService.interview(userName, amountQuestions);
    }

}
