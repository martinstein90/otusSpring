package config;

import dao.InterviewDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.CanYouStandUpForTheTruth;
import service.InterviewService;

@Configuration
public class ServicesConfig {

    @Bean
    public InterviewService getInterviewService(InterviewDao dao) {
        return new CanYouStandUpForTheTruth(dao);
    }
}
