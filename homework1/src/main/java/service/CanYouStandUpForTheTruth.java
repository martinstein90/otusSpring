package service;

import dao.InterviewDao;
import domain.Question;
import domain.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CanYouStandUpForTheTruth implements InterviewService{

    InterviewDao dao;
    private int scores = 0;

    public CanYouStandUpForTheTruth(InterviewDao dao) {
        this.dao = dao;
    }

    @Override
    public void interview(String userName, int amountQuestions) {
        List<Integer> questionsNumbers = getQuestionsShuffleNumbers();

        System.out.println("Привет, " + userName + "!");
        for(int i = 0; i < amountQuestions; i++) {
            Question question = dao.findQuestionByNumber(questionsNumbers.get(i));
            System.out.println("Вопрос " + (i+1) + ": " + question.getQustion());

            String[] answers = question.getAnswers();
            for (int j = 0; j < answers.length; j++)
                System.out.println((j+1) + ") " + answers[j]);

            int selectedAnswerNumber = getSelectedAnswerNumber(answers.length);

            Integer score = question.getScore(answers[selectedAnswerNumber-1]);
            scores += score;
        }

        Result result = dao.getResultByScore(scores * questionsNumbers.size()  / amountQuestions);
        System.out.println("Результат: " +result.getResult() );
        System.out.println(userName + "благодарим за участие!");
    }

    private List<Integer> getQuestionsShuffleNumbers() {
        List<Integer> questionsNumbers = Arrays.asList(dao.getQuestionsNumbers());
        Collections.shuffle(questionsNumbers);
        return questionsNumbers;
    }

    private int getSelectedAnswerNumber(int upperLimit) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int selectedAnswerNumber = -1;
        while(true) {
            String line;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                System.out.println("Что то с клавиатурой!");
                throw new RuntimeException(e);
            }
            try {
                selectedAnswerNumber = Integer.parseInt(line);
            }
            catch (NumberFormatException e)  {
                System.out.println("Не число!");
            }

            if(selectedAnswerNumber > 0 && selectedAnswerNumber <= upperLimit)
                break;
            else
                System.out.println("Введите число от 1 до " + upperLimit);
        }
        return selectedAnswerNumber;
    }
}
