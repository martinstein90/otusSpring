package com.martin.service;

import com.martin.reader.ConsoleReader;
import com.martin.dao.InterviewDao;
import com.martin.domain.Question;
import com.martin.domain.Result;
import com.martin.properties.ApplicationSettings;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Service
public class CanYouStandUpForTheTruthInterviewService implements InterviewService{

    private final InterviewDao dao;
    private final MessageSource messageSource;
    private Locale locale;
    private final ConsoleReader consoleReader;
    private int scores = 0;


    public CanYouStandUpForTheTruthInterviewService(MessageSource messageSource,
                                                    ApplicationSettings settings,
                                                    ConsoleReader consoleReader,
                                                    InterviewDao dao) {
        if(settings.isEnglishLocal())
            this.locale = new Locale("en", "US");
        this.messageSource = messageSource;
        this.consoleReader = consoleReader;
        this.dao = dao;
    }

    @Override
    public void interview(String userName, int amountQuestions) {
        List<Integer> questionsNumbers = getQuestionsShuffleNumbers();

        System.out.println(messageSource.getMessage("hello" , null, locale) + ", " + userName + "!");
        for(int i = 0; i < amountQuestions; i++) {
            Question question = dao.findQuestionByNumber(questionsNumbers.get(i));
            System.out.println(messageSource.getMessage("question" , null, locale) + " " + (i+1) + ": " + question.getQuestion());

            List<String> answers = question.getAnswers();
            for (int j = 0; j < answers.size(); j++)
                System.out.println((j+1) + ") " + answers.get(j));

            int selectedAnswerNumber = getSelectedAnswerNumber(answers.size());

            Integer score = question.getScore(answers.get(selectedAnswerNumber-1));
            scores += score;
        }

        Result result = dao.getResultByScore(scores * questionsNumbers.size()  / amountQuestions);
        System.out.println(messageSource.getMessage("total" , null, locale) + result.getResult() );
        System.out.println(userName + ", " + messageSource.getMessage("thank" , null, locale));
    }

    private List<Integer> getQuestionsShuffleNumbers() {
        List<Integer> questionsNumbers = dao.getQuestionsNumbers();
        Collections.shuffle(questionsNumbers);
        return questionsNumbers;
    }

    private int getSelectedAnswerNumber(int upperLimit) {
        int selectedAnswerNumber;
        while(true) {
            String line;
            try {
                line = consoleReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                selectedAnswerNumber = Integer.parseInt(line);
            }
            catch (NumberFormatException e)  {
                System.out.println(messageSource.getMessage("notNumber" , null, locale));
                continue;
            }

            if(selectedAnswerNumber > 0 && selectedAnswerNumber <= upperLimit)
                break;
            else
                System.out.println(messageSource.getMessage("enterNumberRange" , null, locale) + " 1 - " + upperLimit);
        }
        return selectedAnswerNumber;
    }
}