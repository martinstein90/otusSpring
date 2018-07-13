package com.martin;

import com.martin.reader.ConsoleReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import com.martin.service.InterviewService;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
public class Main {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms =  new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/interview");
        ms.setDefaultEncoding("UTF-8");
        return ms ;
    }

    @Bean
    ConsoleReader consoleReader() {
        return new ConsoleReader();
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
