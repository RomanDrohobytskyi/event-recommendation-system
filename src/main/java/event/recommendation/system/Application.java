package event.recommendation.system;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        configureLog4J();
    }

    private static void configureLog4J(){
        BasicConfigurator.configure();
    }
}