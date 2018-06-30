package dao;

import org.junit.Test;

import java.io.IOException;

public class QuestionFromCSVdaoTest {

    @Test
    public void ttt() {

      try {
          InterviewFromCSVdao q= new InterviewFromCSVdao();
           q .load("src\\main\\resources\\Can you stand up for the truth.csv");
      q.findByScore(1);
      } catch (IOException e) {
            System.out.println("Ex" + e);
        }


    }
}
