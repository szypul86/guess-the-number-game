package academy.learnprogramming.console;

import academy.learnprogramming.config.GameConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Main {
    //private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Guess the number Game");

        //create context
        ConfigurableApplicationContext context
                = new AnnotationConfigApplicationContext(GameConfig.class);

        context.close();
    }
}
