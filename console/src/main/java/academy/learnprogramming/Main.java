package academy.learnprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Guess the number Game");

        //create context
        ConfigurableApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);

        //get number generator bean from context
        NumberGenerator numberGenerator
                = context.getBean(NumberGenerator.class);

        //call method next() to get a random number
        int number = numberGenerator.next();

        logger.info("number = {}", number);

        //get the game bean from context
        /*Game game = context.getBean(Game.class);*/

        //get the messageGenerator Bean
        MessageGenerator messageGenerator = context.getBean(MessageGenerator.class);

        logger.info("{}",messageGenerator.getMainMessage());
        logger.info("{}",messageGenerator.getResultMessage());

        context.close();
    }
}
