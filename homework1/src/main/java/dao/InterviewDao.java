package dao;

import domain.Question;
import domain.Result;

public interface InterviewDao {
    Integer[]   getQuestionsNumbers();
    Question    findQuestionByNumber(int number);
    Result      getResultByScore(int score);
}
