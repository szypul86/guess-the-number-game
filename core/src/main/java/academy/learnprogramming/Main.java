package academy.learnprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static final String CONFIG_LOCATION = "beans.xml";

    public static void main(String[] args) {
        logger.info("Guess the number Game");

        //create context
        ConfigurableApplicationContext context
                = new ClassPathXmlApplicationContext(CONFIG_LOCATION);

        //get number generator bean from context
        NumberGenerator numberGenerator
                = context.getBean("numberGenerator", NumberGenerator.class);

        //call method next() to get a random number
        int number = numberGenerator.next();

        logger.info("number = {}", number);

        //get the game bean from context
        Game game = context.getBean(Game.class);

        //call reset() method
        game.reset();

        context.close();
    }
}
