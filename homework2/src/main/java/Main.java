import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import service.InterviewService;

@Configuration
@PropertySource("classpath:properties")
@ComponentScan
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
