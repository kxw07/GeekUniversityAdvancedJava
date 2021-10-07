package others.spring_practice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import others.spring_practice.config.TestClassB;


@SpringBootApplication
public class PracticeSpringApplication implements CommandLineRunner {
    private final static Logger logger = LogManager.getLogger();

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(PracticeSpringApplication.class);
    }

    @Override
    public void run(String... args) {
//        int count = 0;
//        int ringBufferDefaultSize = 256 * 1024;
//        int arrayBlockingQueueDefaultSize = 1024;
//        for (int i = 0; i < (ringBufferDefaultSize + arrayBlockingQueueDefaultSize + 100); i++) {
//            logger.info("PracticeSpringApplication run... " + ++count);
//        }
//
//        System.out.println("Finish log test");

        TestClassB testClassB = applicationContext.getBean(TestClassB.class);
        System.out.println(testClassB.getName());
        System.out.println(testClassB.getValue());
    }
}
