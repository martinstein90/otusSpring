import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.CanYouStandUpForTheTruth;
import service.InterviewService;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        InterviewService service = context.getBean(CanYouStandUpForTheTruth.class);
        service.interview("Ivan", 5);
    }

}
