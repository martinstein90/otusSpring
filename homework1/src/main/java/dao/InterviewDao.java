package dao;

import domain.Question;
import domain.Result;


public interface InterviewDao {
    Integer[] getQuetionsNumber();
    Question findByNumber(int number);
    Result findByScore(int score);
}
