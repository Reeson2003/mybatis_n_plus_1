package com.test.beans;

import com.test.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by Pavel Gavrilov
 */
@Configuration
@ComponentScan("com.test")
@Component
@PropertySource("classpath:application.properties")
public class Application {
    @Autowired
    private Environment environment;
    @Autowired
    @SERR
//    @SOUT
    private AuthorLogger logger;

    public static void main(String[] args) {
        ApplicationContext context = new  AnnotationConfigApplicationContext(Application.class);
    }

//    @PostConstruct
//    void launch() {
//        logger.log(new Author());
//    }

    @Value("${systemProperties.serr}")
    void printProp(AuthorLogger authorLogger) {
        authorLogger.log(new Author());
    }

//    @Value("SOUT")
//    void launchSout(AuthorLogger authorLogger) {
//        authorLogger.log(new Author());
//    }
}
