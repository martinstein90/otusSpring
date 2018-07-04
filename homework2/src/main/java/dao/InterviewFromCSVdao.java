package dao;

import domain.Question;
import domain.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;

@Repository
public class InterviewFromCSVdao implements InterviewDao {

    private Map<Integer, Question> questions = new HashMap<>();
    private List<Result> results = new ArrayList<>();

    private static final String PARSING_ERROR = "Exception questions load from csv file.\n" +
        "Format for question: N(!=0); Interview; answer1; score1; answer2; score2; answer3; score3; ... answerN; scoreN\n" +
        "Format for results: 0; Result1; Lower limit1; Upper limit1; Result2; Lower limit2; Upper limit2;... ResultN; Lower limitN\n";

    public InterviewFromCSVdao(@Value("${url}")String url) {
        System.out.println("InterviewFromCSVdao(String url), url: " + url);
        try {
            load(url);
        } catch (IOException e) {
            System.out.println(e.getMessage()); //TODO logger
            throw new RuntimeException(e);
        }
    }

    private void load(String url) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(url)));
        String line = reader.readLine();
        while(line !=null) {
            String[] split = line.split(";");
            int number;
            try {
                number = Integer.parseInt(split[0]);
            }
            catch (NumberFormatException e) {
                System.out.println(e.getMessage());//TODO logger!
                throw new RuntimeException(PARSING_ERROR);
            }

            if (split.length > 5 && split.length%2 == 0) {
                if(number != 0)
                    createQuestion(split);
                else
                    createResult(split);
            }
            else
                throw new RuntimeException(PARSING_ERROR);
            line = reader.readLine();
        }
    }

    private void createQuestion(String[] str) {
        int number = Integer.parseInt(str[0]);
        String question = str[1];
        Map<String, Integer> answers = new HashMap<>();
        for(int i = 2; i < str.length-1; i += 2){
            String answer = str[i];
            int score;
            try {
                score = Integer.parseInt(str[i+1]);
            }
            catch (NumberFormatException e) {
                System.out.println(e.getMessage());//TODO logger!
                throw new RuntimeException(PARSING_ERROR);
            }
            answers.put(answer, score);
        }
        questions.put(number, new Question( question, answers));
    }

    private void createResult(String[] str) {
        for(int i= 1; i<str.length - 1; i+=3) {
            String res = str[i];
            int lowerLimitScore, upperLimitScore;
            try {
                lowerLimitScore = Integer.parseInt(str[i+1]);
                upperLimitScore = (i == (str.length - 2)) ? Integer.MAX_VALUE : Integer.parseInt(str[i + 2]);
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

