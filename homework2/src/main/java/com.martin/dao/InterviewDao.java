package com.martin.dao;

import com.martin.domain.Question;
import com.martin.domain.Result;

import java.util.List;

public interface InterviewDao {
    List<Integer>   getQuestionsNumbers();
    Question        findQuestionByNumber(int number);
    Result          getResultByScore(int score);
}
