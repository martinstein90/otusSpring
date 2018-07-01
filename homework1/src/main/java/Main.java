import dao.InterviewDao;
import dao.InterviewFromCSVdao;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.CanYouStandUpForTheTruth;
import service.InterviewService;

public class Main {

    public static void main(String[] args) {
        //InterviewFromCSVdao("homework1\\src\\main\\resources\\Can you stand up for the truth.csv");


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        InterviewDao dao = context.getBean(InterviewFromCSVdao.class);
        InterviewService service = context.getBean(CanYouStandUpForTheTruth.class);
        service.interview("Ivan", 5);
    }

}
