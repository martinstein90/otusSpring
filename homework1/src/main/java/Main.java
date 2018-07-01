import dao.InterviewDao;
import dao.InterviewFromCSVdao;
import service.CanYouStandUpForTheTruth;
import service.InterviewService;

public class Main {

    public static void main(String[] args) {
        InterviewDao dao = new InterviewFromCSVdao("homework1\\src\\main\\resources\\Can you stand up for the truth.csv");
        InterviewService service = new CanYouStandUpForTheTruth(dao);
        service.interview("Ivan", 5);
    }

}
