package dao;

import domain.Question;
import domain.Result;
import org.omg.SendingContext.RunTime;

import java.io.*;
import java.util.*;

public class InterviewFromCSVdao implements InterviewDao {

    private Map<Integer, Question> questions = new HashMap<>();
    private List<Result> results = new ArrayList<>();

    public InterviewFromCSVdao(String url) {
        try {
            load(url);
        } catch (IOException e) {
            throw new RuntimeException("");
        }
    }

    public  void load(String url) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(url)));
        String line = reader.readLine();
        while(line !=null) {
            String[] split = line.split(";");
            int number = Integer.parseInt(split[0]);
            if (split.length > 5 && split.length%2 == 0) {
                if(number != 0)
                    createQuestion(split);
                else
                    createResult(split);
            }
            else
                throw new IllegalStateException("Exception questions load from csv file.\n" +
                        "Format for question: N(!=0); Interview; answer1; score1; answer2; score2; answer3; score3; ... answerN; scoreN\n" +
                        "Format for results: 0; Result1; Lower limit1; Upper limit1; Result2; Lower limit2; Upper limit2;... ResultN; Lower limitN\n");
            line = reader.readLine();
        }
    }

    private void createQuestion(String[] str) {
        int number = Integer.parseInt(str[0]);
        String question = str[1];
        Map<String, Integer> answers = new HashMap<>();
        for(int i = 2; i < str.length-1; i += 2){
            String answer = str[i];
            int score = Integer.parseInt(str[i+1]);
            answers.put(answer, score);
        }
        questions.put(number, new Question( question, answers));
    }

    private void createResult(String[] str) {
        for(int i= 1; i<str.length - 1; i+=3) {
            String res = str[i];
            int lowerLimitScore = Integer.parseInt(str[i+1]);
            int upperLimitScore = (i == str.length - 2) ? Integer.MAX_VALUE : Integer.parseInt(str[i+2]);
            results.add(new Result(res, lowerLimitScore, upperLimitScore));
        }
    }

    public Integer[] getQuetionsNumber() {
        return (Integer[])(questions.keySet().toArray());
    }

    @Override
    public Question findByNumber(int number) {
        return questions.get(number);
    }

    @Override
    public Result findByScore(int score) {
        for (Result result: results) {
            if(result.getLowerLimitScore() <= score && result.getUpperLimitScore() > score)
                return result;
        }
        return null;
    }




}

