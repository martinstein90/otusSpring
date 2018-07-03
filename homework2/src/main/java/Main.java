import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import service.InterviewService;

@Configuration
@ComponentScan(basePackages = "config")
public class Main {

    @Bean
    public static PropertySourcesPlaceholderConfigurer property() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        InterviewService service = context.getBean(InterviewService.class);
        service.interview("Ivan", 5);
    }

}