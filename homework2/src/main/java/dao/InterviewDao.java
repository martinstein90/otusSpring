package dao;

import domain.Question;
import domain.Result;

import java.util.List;

public interface InterviewDao {
    List<Integer>   getQuestionsNumbers();
    Question        findQuestionByNumber(int number);
    Result          getResultByScore(int score);
}
