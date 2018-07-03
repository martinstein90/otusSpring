package config;

import dao.InterviewDao;
import dao.InterviewFromCSVdao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("file://src//main//resources//properties")
@Configuration
public class DaoConfig {

    @Bean
    public InterviewDao getInterviewDao(@Value("url")String url) {
        return new InterviewFromCSVdao(url);
    }
}
