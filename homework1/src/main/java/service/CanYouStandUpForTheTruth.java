package service;

import dao.InterviewDao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CanYouStandUpForTheTruth implements InterviewService{

    InterviewDao dao;

    public CanYouStandUpForTheTruth(InterviewDao dao) {
        this.dao = dao;
    }

    @Override
    public void interview(String name) {

        List<Integer> integers = Arrays.asList(dao.getQuetionsNumber());
        Collections.shuffle(integers);
        System.out.println(integers);


        System.out.println(dao.getQuetionsNumber());
    }
}
