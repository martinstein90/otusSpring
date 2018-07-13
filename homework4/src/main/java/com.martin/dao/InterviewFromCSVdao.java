package com.martin.dao;

import com.martin.domain.Question;
import com.martin.domain.Result;
import com.martin.properties.ApplicationSettings;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
@ConditionalOnProperty(value = "application.test", havingValue = "true") //поэкспериментировать
public class InterviewFromCSVdao implements InterviewDao {

    private final MessageSource source;
    private Map<Integer, Question> questions = new HashMap<>();
    private List<Result> results = new ArrayList<>();
    private Locale locale;

    private static final String PARSING_ERROR = "Exception questions load from csv file.\n" +
            "Format for question: N(!=0); Interview; answer1; score1; answer2; score2; answer3; score3; ... answerN; scoreN\n" +
            "Format for results: 0; Result1; Lower limit1; Upper limit1; Result2; Lower limit2; Upper limit2;... ResultN; Lower limitN\n";

    public InterviewFromCSVdao(MessageSource ms, ApplicationSettings settings) {
        this.source = ms;
        initLocale(settings.isEnglishLocal());
        System.out.println("InterviewFromCSVdao:: settings.isEnglishLocal()"
                + settings.isEnglishLocal());
        loadQuestions();
        loadResults();
    }

    private void initLocale(boolean isEnglishLocal) {
        if(isEnglishLocal)
            this.locale = new Locale("en", "US");
    }

    private void loadQuestions() {
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            String strQuestion;
            try {
                strQuestion = source.getMessage("question" + i, null, locale);
                createQuestion(i, strQuestion);
            }
            catch(NoSuchMessageException e) {
                return;
            }
        }
    }

    private void loadResults() {
        String strResult = source.getMessage("result" ,null, locale);
        if(strResult != null)
            createResult(strResult);
    }

    private void createQuestion(int number, String str) {
        String[] split = str.split(";");
        String question = split[0];
        Map<String, Integer> answers = new HashMap<>();
        for(int i = 1; i < split.length-1; i += 2){
            String answer = split[i];
            int score;
            try {
                score = Integer.parseInt(split[i+1]);
            }
            catch (NumberFormatException e) {
                System.out.println(e.getMessage());//TODO logger!
                throw new RuntimeException(PARSING_ERROR);
            }
            answers.put(answer, score);
        }
        questions.put(number, new Question( question, answers));
    }

    private void createResult(String str) {
        String[] split = str.split(";");
        for(int i= 0; i<split.length - 1; i+=3) {
            String res = split[i];
            int lowerLimitScore, upperLimitScore;
            try {
                lowerLimitScore = Integer.parseInt(split[i+1]);
                upperLimitScore = (i == (split.length - 2)) ? Integer.MAX_VALUE : Integer.parseInt(split[i + 2]);
            }
            catch (NumberFormatException e) {
                System.out.println(e.getMessage());//TODO logger!
                throw new RuntimeException(PARSING_ERROR);
            }
            results.add(new Result(res, lowerLimitScore, upperLimitScore));
        }
    }

    public List<Integer> getQuestionsNumbers() {
        return new ArrayList<>(questions.keySet());
    }

    @Override
    public Question findQuestionByNumber(int number) {
        return questions.get(number);
    }

    @Override
    public Result getResultByScore(int score) {
        for (Result result: results) {
            if(result.getLowerLimitScore() <= score && result.getUpperLimitScore() > score)
                return result;
        }
        return null;
    }

}